package com.jdpadron98carlosmc98.cheapfashionapp.SplashScreen;

import java.lang.ref.WeakReference;

public interface SplashScreenContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void goToLogin();
    }

    interface Presenter {

        void goToLogin();

        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void injectRouter(Router router);
    }

    interface Model {
    }

    interface Router {

        void passDataToNextScreen(SplashScreenState state);

        SplashScreenState getDataFromPreviousScreen();
    }
}
