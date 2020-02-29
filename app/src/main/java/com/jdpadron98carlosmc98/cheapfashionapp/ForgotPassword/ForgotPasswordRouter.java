package com.jdpadron98carlosmc98.cheapfashionapp.ForgotPassword;

import android.content.Context;
import android.content.Intent;

import com.jdpadron98carlosmc98.cheapfashionapp.app.AppMediator;

public class ForgotPasswordRouter implements ForgotPasswordContract.Router {

    public static String TAG = ForgotPasswordRouter.class.getSimpleName();

    private AppMediator mediator;

    public ForgotPasswordRouter(AppMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void navigateToNextScreen() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, ForgotPasswordActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void passDataToNextScreen(ForgotPasswordState state) {
        mediator.setForgotPasswordState(state);
    }

    @Override
    public ForgotPasswordState getDataFromPreviousScreen() {
        ForgotPasswordState state = mediator.getForgotPasswordState();
        return state;
    }
}
