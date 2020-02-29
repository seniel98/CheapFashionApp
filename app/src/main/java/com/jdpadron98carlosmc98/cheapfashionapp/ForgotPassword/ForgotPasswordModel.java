package com.jdpadron98carlosmc98.cheapfashionapp.ForgotPassword;

import com.jdpadron98carlosmc98.cheapfashionapp.app.Contract;

public class ForgotPasswordModel implements ForgotPasswordContract.Model {

    public static String TAG = ForgotPasswordModel.class.getSimpleName();

    private Contract repository;

    public ForgotPasswordModel(Contract repository) {
        this.repository = repository;
    }

  /*  @Override
    public String fetchData() {
        // Log.e(TAG, "fetchData()");
        return "Hello";
    }*/
}
