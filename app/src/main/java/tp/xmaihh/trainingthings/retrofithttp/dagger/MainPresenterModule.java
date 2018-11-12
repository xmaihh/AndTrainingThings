package tp.xmaihh.trainingthings.retrofithttp.dagger;

import dagger.Module;
import dagger.Provides;
import tp.xmaihh.trainingthings.retrofithttp.ui.MainContract;
import tp.xmaihh.trainingthings.retrofithttp.ui.MainPresenter;

@Module
public class MainPresenterModule {
    MainContract.View mView;

    public MainPresenterModule(MainContract.View mView) {
        this.mView = mView;
    }

    @Provides
    MainPresenter providesPresenter() {
        return new MainPresenter(mView);
    }
}
