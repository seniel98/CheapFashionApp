package com.jdpadron98carlosmc98.cheapfashionapp.Login;

import java.lang.ref.WeakReference;

public class LoginPresenter implements LoginContract.Presenter {

    public static String TAG = LoginPresenter.class.getSimpleName();

    private WeakReference<LoginContract.View> view;
    private LoginViewModel viewModel;
    private LoginContract.Model model;
    private LoginContract.Router router;

    public LoginPresenter(LoginState state) {
        viewModel = state;
    }


    @Override
    public void onResume() {

        view.get().cleanErrorInputs();
        view.get().clearInputFocus();
    }

    @Override
    public void checkLogin(String emailStr, String passStr) {
        if (emailStr.isEmpty() && passStr.isEmpty()) {
            view.get().setErrorLayoutInputs(2);
        } else if (emailStr.isEmpty()) {
            view.get().setErrorLayoutInputs(0);
        } else if (passStr.isEmpty()) {
            view.get().setErrorLayoutInputs(1);
        } else{
            //view.get().enableProgressBar();
            //callModel
            goToHomeRouter();
        }
    }

    @Override
    public void injectView(WeakReference<LoginContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(LoginContract.Model model) {
        this.model = model;
    }

    @Override
    public void injectRouter(LoginContract.Router router) {
        this.router = router;
    }

    @Override
    public void goToHomeRouter() {
        view.get().goToHome();
    }

    @Override
    public void goToSignUpRouter() {
        view.get().goToSignUp();
    }

    @Override
    public void goToForgotPasswordRouter() {
        view.get().goToForgotPassword();
    }
}
