package com.jdpadron98carlosmc98.cheapfashionapp.SplashScreen;

import com.jdpadron98carlosmc98.cheapfashionapp.app.RepositoryContract;

public class SplashScreenModel implements SplashScreenContract.Model {

    public static String TAG = SplashScreenModel.class.getSimpleName();

    private RepositoryContract repository;

    public SplashScreenModel(RepositoryContract repository) {

        this.repository = repository;

    }
}
