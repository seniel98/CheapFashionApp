package com.jdpadron98carlosmc98.cheapfashionapp.SplashScreen;

import com.jdpadron98carlosmc98.cheapfashionapp.app.AppMediator;

public class SplashScreenRouter implements SplashScreenContract.Router {

    public static String TAG = SplashScreenRouter.class.getSimpleName();

    private AppMediator mediator;

    public SplashScreenRouter(AppMediator mediator) {
        this.mediator = mediator;
    }


    @Override
    public void passDataToNextScreen(SplashScreenState state) {
        mediator.setSplashScreenState(state);
    }

    @Override
    public SplashScreenState getDataFromPreviousScreen() {
        SplashScreenState state = mediator.getSplashScreenState();
        return state;
    }
}
