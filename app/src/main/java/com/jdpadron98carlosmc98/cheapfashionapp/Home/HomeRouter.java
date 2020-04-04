package com.jdpadron98carlosmc98.cheapfashionapp.Home;

import android.content.Intent;
import android.content.Context;

import com.jdpadron98carlosmc98.cheapfashionapp.app.AppMediator;

public class HomeRouter implements HomeContract.Router {

    public static String TAG = HomeRouter.class.getSimpleName();

    private AppMediator mediator;

    public HomeRouter(AppMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void navigateToNextScreen() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void passStateToNextScreen(HomeState state) {
        mediator.setNextHomeScreenState(state);
    }

    @Override
    public void passStateToPreviousScreen(HomeState state) {
        mediator.setPreviousHomeScreenState(state);
    }

    @Override
    public HomeState getStateFromPreviousScreen() {
        return mediator.getPreviousHomeScreenState();
    }

    @Override
    public HomeState getStateFromNextScreen() {
        return mediator.getNextHomeScreenState();
    }
}
