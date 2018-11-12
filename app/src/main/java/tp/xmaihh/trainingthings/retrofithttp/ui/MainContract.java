package tp.xmaihh.trainingthings.retrofithttp.ui;

import tp.xmaihh.trainingthings.retrofithttp.bean.CookBook;

public interface MainContract {
    interface View {
        void showProgress();

        void hideProgress();

        void displayInfo(CookBook cookbook);
    }

    interface Presenter {
        void getCookbookResultServer();
    }
}
