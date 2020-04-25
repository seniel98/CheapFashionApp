package com.jdpadron98carlosmc98.cheapfashionapp.Login;

import android.content.Context;
import android.content.Intent;

import com.jdpadron98carlosmc98.cheapfashionapp.ForgotPassword.ForgotPasswordActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.Home.HomeActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.SignUp.SignUpActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.app.AppMediator;

public class LoginRouter implements LoginContract.Router {

    public static String TAG = LoginRouter.class.getSimpleName();

    private AppMediator mediator;

    public LoginRouter(AppMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void passDataToNextScreen(LoginState state) {
        mediator.setLoginState(state);
    }

    @Override
    public LoginState getDataFromPreviousScreen() {
        LoginState state = mediator.getLoginState();
        return state;
    }


}
