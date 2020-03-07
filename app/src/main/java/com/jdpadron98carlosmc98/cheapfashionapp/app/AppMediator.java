package com.jdpadron98carlosmc98.cheapfashionapp.app;

import android.app.Application;

import com.jdpadron98carlosmc98.cheapfashionapp.ForgotPassword.ForgotPasswordState;
import com.jdpadron98carlosmc98.cheapfashionapp.Login.LoginState;
import com.jdpadron98carlosmc98.cheapfashionapp.Profile.ProfileState;
import com.jdpadron98carlosmc98.cheapfashionapp.SignUp.SignUpState;
import com.jdpadron98carlosmc98.cheapfashionapp.SplashScreen.SplashScreenState;

public class AppMediator extends Application {

    private SplashScreenState splashScreenState;
    private LoginState loginState;
    private SignUpState signUpState;
    private ForgotPasswordState forgotPasswordState;
    private ProfileState profileState;


    public ProfileState getProfileState() {
        return profileState;
    }

    public void setProfileState(ProfileState profileState) {
        this.profileState = profileState;
    }

    public SignUpState getSignUpState() {
        return signUpState;
    }

    public void setSignUpState(SignUpState signUpState) {
        this.signUpState = signUpState;
    }

    public ForgotPasswordState getForgotPasswordState() {
        return forgotPasswordState;
    }

    public void setForgotPasswordState(ForgotPasswordState forgotPasswordState) {
        this.forgotPasswordState = forgotPasswordState;
    }

    public LoginState getLoginState() {
        return loginState;
    }

    public void setLoginState(LoginState loginState) {
        this.loginState = loginState;
    }

    public SplashScreenState getSplashScreenState() {
        return splashScreenState;
    }

    public void setSplashScreenState(SplashScreenState splashScreenState) {
        this.splashScreenState = splashScreenState;
    }
}
