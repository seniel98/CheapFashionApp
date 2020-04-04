package com.jdpadron98carlosmc98.cheapfashionapp.ChangePassword;

import android.util.Log;
import android.content.Intent;
import android.content.Context;

import com.jdpadron98carlosmc98.cheapfashionapp.app.AppMediator;

public class ChangePasswordRouter implements ChangePasswordContract.Router {

    public static String TAG = ChangePasswordRouter.class.getSimpleName();

    private AppMediator mediator;

    public ChangePasswordRouter(AppMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void navigateToNextScreen() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, ChangePasswordActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void passStateToNextScreen(ChangePasswordState state) {
        mediator.setNextChangePasswordScreenState(state);
    }

    @Override
    public void passStateToPreviousScreen(ChangePasswordState state) {
        mediator.setPreviousChangePasswordScreenState(state);
    }

    @Override
    public ChangePasswordState getStateFromPreviousScreen() {
        return mediator.getPreviousChangePasswordScreenState();
    }

    @Override
    public ChangePasswordState getStateFromNextScreen() {
        return mediator.getNextChangePasswordScreenState();
    }
}
