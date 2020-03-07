package com.jdpadron98carlosmc98.cheapfashionapp.ForgotPassword;

import com.jdpadron98carlosmc98.cheapfashionapp.app.RepositoryContract;

public class ForgotPasswordModel implements ForgotPasswordContract.Model {

    public static String TAG = ForgotPasswordModel.class.getSimpleName();

    private RepositoryContract repository;

    public ForgotPasswordModel(RepositoryContract repository) {
        this.repository = repository;
    }

  /*  @Override
    public String fetchData() {
        // Log.e(TAG, "fetchData()");
        return "Hello";
    }*/
}
