package com.jdpadron98carlosmc98.cheapfashionapp.SplashScreen;

import android.content.Context;
import android.content.Intent;

import com.jdpadron98carlosmc98.cheapfashionapp.Login.LoginActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.app.AppMediator;

public class SplashScreenRouter implements SplashScreenContract.Router {

    public static String TAG = SplashScreenRouter.class.getSimpleName();

    private AppMediator mediator;

    public SplashScreenRouter(AppMediator mediator) {
        this.mediator = mediator;
    }


    @Override
    public void goToLogin() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void navigateToNextScreen() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, SplashScreenActivity.class);
        context.startActivity(intent);
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
