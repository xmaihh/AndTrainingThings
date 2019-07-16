package com.example.treeview;

import android.annotation.SuppressLint;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

import com.example.treeview.treeview.Dept;
import com.example.treeview.treeview.Node;
import com.example.treeview.treeview.NodeHelper;
import com.example.treeview.treeview.NodeTreeAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class treeViewActivity extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    private ListView mListView;
    private NodeTreeAdapter mAdapter;
    private LinkedList<Node> mLinkedList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tree_view);

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);
        initView();
        initData();

        mContentView.setVisibility(View.GONE);
        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                toggle(); //点击不做操作//TODO at 9 May,2019
            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    void initView() {
        mListView = findViewById(R.id.id_tree);
        mAdapter = new NodeTreeAdapter(mLinkedList, mListView, this);
        mListView.setAdapter(mAdapter);
    }

    void initData() {
        List<Node> data = new ArrayList<>();
        addOne(data);
        mLinkedList.addAll(NodeHelper.sortNodes(data));
        mAdapter.notifyDataSetChanged();
    }

    void addOne(List<Node> data) {
        data.add(new Dept(1, 0, "总公司"));//可以直接注释掉此项，即可构造一个森林
        data.add(new Dept(2, 1, "一级部门"));
        data.add(new Dept(3, 1, "一级部门"));
        data.add(new Dept(4, 1, "一级部门"));

        data.add(new Dept(222, 5, "二级部门--测试1"));
        data.add(new Dept(223, 5, "二级部门--测试2"));

        data.add(new Dept(5, 1, "一级部门"));

        data.add(new Dept(224, 5, "二级部门--测试3"));
        data.add(new Dept(225, 5, "二级部门--测试4"));

        data.add(new Dept(6, 1, "一级部门"));
        data.add(new Dept(7, 1, "一级部门"));
        data.add(new Dept(8, 1, "一级部门"));
        data.add(new Dept(9, 1, "一级部门"));
        data.add(new Dept(10, 1, "一级部门"));

        for (int i = 2; i <= 10; i++) {
            for (int j = 0; j < 10; j++) {
                data.add(new Dept(1 + (i - 1) * 10 + j, i, "二级部门" + j));
            }
        }

        for (int i = 0; i < 5; i++) {
            data.add(new Dept(101 + i, 11, "三级部门" + i));
        }

        for (int i = 0; i < 5; i++) {
            data.add(new Dept(106 + i, 22, "三级部门" + i));
        }
        for (int i = 0; i < 5; i++) {
            data.add(new Dept(111 + i, 33, "三级部门" + i));
        }
        for (int i = 0; i < 5; i++) {
            data.add(new Dept(115 + i, 44, "三级部门" + i));
        }

        for (int i = 0; i < 5; i++) {
            data.add(new Dept(401 + i, 101, "四级部门" + i));
        }

    }
}
