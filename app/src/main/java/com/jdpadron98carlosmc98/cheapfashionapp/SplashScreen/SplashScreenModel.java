package com.jdpadron98carlosmc98.cheapfashionapp.SplashScreen;

import com.jdpadron98carlosmc98.cheapfashionapp.app.Contract;

public class SplashScreenModel implements SplashScreenContract.Model {

    public static String TAG = SplashScreenModel.class.getSimpleName();

    private Contract repository;

    public SplashScreenModel(Contract repository) {

        this.repository = repository;

    }
}
