package tp.xmaihh.trainingthings.retrofithttp.dagger;

import dagger.Component;
import tp.xmaihh.trainingthings.retrofithttp.ui.RetrofitActivity;

@Component(modules = {MainPresenterModule.class})
public interface MainActivityComponent {
    void inject(RetrofitActivity mainActivity);
}
