package com.jdpadron98carlosmc98.cheapfashionapp.SplashScreen;

import com.jdpadron98carlosmc98.cheapfashionapp.app.RepositoryContract;

import java.lang.ref.WeakReference;

public interface SplashScreenContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void goToLogin();

        void goToHome();
    }

    interface Presenter {

        void goToLogin();

        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void injectRouter(Router router);

        void goToHome();

        void checkSession();
    }

    interface Model {
        void checkSession(RepositoryContract.OnLoggedInCallback loggedInCallback);
    }

    interface Router {

        void passDataToNextScreen(SplashScreenState state);

        SplashScreenState getDataFromPreviousScreen();
    }
}
