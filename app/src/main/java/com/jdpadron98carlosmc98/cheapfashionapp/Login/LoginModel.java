package com.jdpadron98carlosmc98.cheapfashionapp.Login;

import com.jdpadron98carlosmc98.cheapfashionapp.app.RepositoryContract;

public class LoginModel implements LoginContract.Model {

    public static String TAG = LoginModel.class.getSimpleName();

    private RepositoryContract repository;

    public LoginModel(RepositoryContract repository) {
        this.repository = repository;
    }

    @Override
    public void signIn(String emailStr, String passStr, RepositoryContract.OnSignInCallback onSignInCallback) {
        repository.signIn(emailStr, passStr, onSignInCallback);
    }
}
