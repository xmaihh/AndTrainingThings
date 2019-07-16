package tp.xmaihh.trainingthings.multitype;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;
import tp.xmaihh.trainingthings.R;
import tp.xmaihh.trainingthings.multitype.comm.Category;
import tp.xmaihh.trainingthings.multitype.comm.CategoryItemViewBinder;

public class MultiTypeActivity extends AppCompatActivity {

    private static final int SPAN_COUNT = 2;
    List<Object> items;
    MultiTypeAdapter adapter;

    private static final class JsonData {
        private static final String PREFIX = "这是一段长长的标题文字要凑齐两行";
        private Post post00 = new Post(R.drawable.ic_no_pic, PREFIX + "post00");
        private Post post01 = new Post(R.drawable.ic_no_pic, PREFIX + "post01");
        private Post post02 = new Post(R.drawable.ic_no_pic, PREFIX + "post02");
        private Post post03 = new Post(R.drawable.ic_no_pic, PREFIX + "post03");

        Category category0 = new Category("title0");
        Post[] postArray = {post00, post01, post02, post03};
        List<Post> postList = new ArrayList<>();

        {
            postList.add(post00);
            postList.add(post00);
            postList.add(post00);
            postList.add(post00);
            postList.add(post00);
            postList.add(post00);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_type);

        adapter = new MultiTypeAdapter();
        adapter.register(Category.class, new CategoryItemViewBinder());
        adapter.register(Post.class, new PostViewBinder());
        adapter.register(PostList.class, new HorizontalPostViewBinder());

        RecyclerView recyclerView = findViewById(R.id.list);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, SPAN_COUNT);
        GridLayoutManager.SpanSizeLookup spanSizeLookup = new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                Object item = items.get(position);
                return (item instanceof PostList || item instanceof Category) ? SPAN_COUNT : 1;
            }
        };

        layoutManager.setSpanSizeLookup(spanSizeLookup);
        recyclerView.setLayoutManager(layoutManager);
        int space = getResources().getDimensionPixelSize(R.dimen.normal_space);

        recyclerView.addItemDecoration(new PostItemDecoration(space, spanSizeLookup));

        recyclerView.setAdapter(adapter);

        JsonData data = new JsonData();
        items = new ArrayList<>();
        /* You also could use Category as your CategoryItemContent directly */
        for (int i = 0; i < 10; i++) {
            items.add(data.category0);
            items.add(data.postArray[0]);
            items.add(data.postArray[1]);
            items.add(data.postArray[2]);
            items.add(data.postArray[3]);
            items.add(data.postArray[0]);
            items.add(data.postArray[4]);
            items.add(new PostList(data.postList));
        }
        adapter.setItems(items);
        adapter.notifyDataSetChanged();
    }
}
