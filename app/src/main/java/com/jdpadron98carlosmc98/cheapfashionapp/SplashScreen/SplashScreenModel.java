package com.jdpadron98carlosmc98.cheapfashionapp.SplashScreen;

import com.jdpadron98carlosmc98.cheapfashionapp.data.RepositoryContract;

public class SplashScreenModel implements SplashScreenContract.Model {

    public static String TAG = SplashScreenModel.class.getSimpleName();

    private RepositoryContract repository;

    public SplashScreenModel(RepositoryContract repository) {

        this.repository = repository;

    }

    @Override
    public void checkSession(RepositoryContract.OnLoggedInCallback loggedInCallback) {
        repository.isLoggedIn(loggedInCallback);
    }
}
