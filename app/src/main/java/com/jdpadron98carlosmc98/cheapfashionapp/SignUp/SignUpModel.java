package com.jdpadron98carlosmc98.cheapfashionapp.SignUp;

import com.jdpadron98carlosmc98.cheapfashionapp.app.Contract;

public class SignUpModel implements SignUpContract.Model {

    public static String TAG = SignUpModel.class.getSimpleName();

    private Contract repository;

    public SignUpModel(Contract repository) {
        this.repository = repository;
    }

  /*  @Override
    public String fetchData() {
        // Log.e(TAG, "fetchData()");
        return "Hello";
    }*/
}
