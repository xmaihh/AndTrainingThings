package tp.xmaihh.trainingthings.retrofithttp.dagger;

import dagger.Component;
import tp.xmaihh.trainingthings.retrofithttp.ui.MainPresenter;

@Component(modules = {CookbookModule.class})
public interface MainPresenterComponent {
    void inject(MainPresenter mainPresenter);
}
