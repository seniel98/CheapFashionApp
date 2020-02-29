package com.jdpadron98carlosmc98.cheapfashionapp;

public class SignUpModel implements SignUpContract.Model {

    public static String TAG = SignUpModel.class.getSimpleName();

    public SignUpModel() {

    }

    @Override
    public String fetchData() {
        // Log.e(TAG, "fetchData()");
        return "Hello";
    }
}
