package com.jdpadron98carlosmc98.cheapfashionapp.SplashScreen;

import com.jdpadron98carlosmc98.cheapfashionapp.app.RepositoryContract;

import java.lang.ref.WeakReference;

public class SplashScreenPresenter implements SplashScreenContract.Presenter {

    public static String TAG = SplashScreenPresenter.class.getSimpleName();

    private WeakReference<SplashScreenContract.View> view;
    private SplashScreenViewModel viewModel;
    private SplashScreenContract.Model model;
    private SplashScreenContract.Router router;

    public SplashScreenPresenter(SplashScreenState state) {
        viewModel = state;
    }


    @Override
    public void goToLogin() {
        view.get().goToLogin();
    }

    public void goToHome() {
        view.get().goToHome();
    }


    @Override
    public void checkSession() {
        model.checkSession(new RepositoryContract.OnLoggedInCallback() {
            @Override
            public void onLoggedIn(boolean isLoggedIn) {
                if (isLoggedIn) {
                    view.get().goToHome();
                } else {
                    view.get().goToLogin();
                }
            }
        });
    }

    @Override
    public void injectView(WeakReference<SplashScreenContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(SplashScreenContract.Model model) {
        this.model = model;
    }

    @Override
    public void injectRouter(SplashScreenContract.Router router) {
        this.router = router;
    }
}
