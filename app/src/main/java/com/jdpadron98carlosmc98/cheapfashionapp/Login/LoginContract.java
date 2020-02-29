package com.jdpadron98carlosmc98.cheapfashionapp.Login;

import java.lang.ref.WeakReference;

public interface LoginContract {

    interface View {
        void injectPresenter(Presenter presenter);
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void injectRouter(Router router);

        void goToSignUpRouter();
    }

    interface Model {
    }

    interface Router {
        void navigateToNextScreen();

        void passDataToNextScreen(LoginState state);

        LoginState getDataFromPreviousScreen();

        void goToSignUp();
    }
}
