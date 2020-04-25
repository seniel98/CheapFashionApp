package com.jdpadron98carlosmc98.cheapfashionapp.Login;

import java.lang.ref.WeakReference;

public interface LoginContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void setErrorLayoutInputs(int value);

        void cleanErrorInputs();

        void clearInputFocus();

        void goToSignUp();

        void goToForgotPassword();

        void goToHome();

        void navigateToNextScreen();

    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void injectRouter(Router router);

        void goToSignUpRouter();

        void goToForgotPasswordRouter();

        void goToHomeRouter();

        void checkLogin(String emailStr, String passStr);

        void onResume();
    }

    interface Model {
    }

    interface Router {

        void passDataToNextScreen(LoginState state);

        LoginState getDataFromPreviousScreen();


    }
}
