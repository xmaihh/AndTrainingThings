package tp.xmaihh.trainingthings.retrofithttp.ui;

import android.view.animation.ScaleAnimation;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import tp.xmaihh.trainingthings.retrofithttp.bean.CookBook;
import tp.xmaihh.trainingthings.retrofithttp.dagger.CookbookModule;
import tp.xmaihh.trainingthings.retrofithttp.dagger.DaggerMainPresenterComponent;
import tp.xmaihh.trainingthings.retrofithttp.retrofit.CookbookService;

public class MainPresenter implements MainContract.Presenter {
    @Inject
    CookbookService cookbookService;

    private MainContract.View mView;

    public MainPresenter(MainContract.View mView) {
        this.mView = mView;
        DaggerMainPresenterComponent.builder()
                .cookbookModule(new CookbookModule())
                .build()
                .inject(this);
    }


    @Override
    public void getCookbookResultServer() {
        cookbookService.getCookBookInfo(
                "0a4459b302a62181ab02f95294c07d5e",
                "西红柿",
                "10",
                "3"
        ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CookBook>() {
                    @Override
                    public void accept(CookBook cookBook) throws Exception {
                        mView.displayInfo(cookBook);
                        mView.hideProgress();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.hideProgress();
                    }
                });
    }
}
