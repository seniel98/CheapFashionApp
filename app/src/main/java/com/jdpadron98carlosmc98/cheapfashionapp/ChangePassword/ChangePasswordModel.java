package com.jdpadron98carlosmc98.cheapfashionapp.ChangePassword;

import android.util.Log;

public class ChangePasswordModel implements ChangePasswordContract.Model {

    public static String TAG = ChangePasswordModel.class.getSimpleName();

    private String data;

    public ChangePasswordModel(String data) {
        this.data = data;
    }

    @Override
    public String getStoredData() {
        // Log.e(TAG, "getStoredData()");
        return data;
    }

    @Override
    public void onRestartScreen(String data) {
        // Log.e(TAG, "onRestartScreen()");
    }

    @Override
    public void onDataFromNextScreen(String data) {
        // Log.e(TAG, "onDataFromNextScreen()");
    }

    @Override
    public void onDataFromPreviousScreen(String data) {
        // Log.e(TAG, "onDataFromPreviousScreen()");
    }
}
