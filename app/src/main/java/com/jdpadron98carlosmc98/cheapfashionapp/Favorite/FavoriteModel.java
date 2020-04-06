package com.jdpadron98carlosmc98.cheapfashionapp.Favorite;

import android.util.Log;

public class FavoriteModel implements FavoriteContract.Model {

    public static String TAG = FavoriteModel.class.getSimpleName();

    private String data;

    public FavoriteModel(String data) {
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
