package tp.xmaihh.trainingthings.retrofithttp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;

import javax.inject.Inject;

import tp.xmaihh.trainingthings.R;
import tp.xmaihh.trainingthings.retrofithttp.bean.CookBook;
import tp.xmaihh.trainingthings.retrofithttp.dagger.DaggerMainActivityComponent;
import tp.xmaihh.trainingthings.retrofithttp.dagger.MainPresenterModule;
import tp.xmaihh.trainingthings.retrofithttp.ui.MainContract.View;

public class RetrofitActivity extends AppCompatActivity implements View {

    @Inject
    MainPresenter mPresenter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retofit_http);
        DaggerMainActivityComponent.builder()
                .mainPresenterModule(new MainPresenterModule(this))
                .build()
                .inject(this);
        mPresenter.getCookbookResultServer();
        progressBar = findViewById(R.id.progressBar);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(android.view.View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(android.view.View.GONE);
    }

    @Override
    public void displayInfo(CookBook cookbook) {
        Log.d("retrofitHttp", "displayInfo: " + cookbook.toString());
    }
}
