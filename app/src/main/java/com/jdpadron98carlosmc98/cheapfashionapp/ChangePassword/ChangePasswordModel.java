package com.jdpadron98carlosmc98.cheapfashionapp.ChangePassword;

import com.jdpadron98carlosmc98.cheapfashionapp.data.RepositoryContract;

public class ChangePasswordModel implements ChangePasswordContract.Model {

    public static String TAG = ChangePasswordModel.class.getSimpleName();

    private String data;

    private RepositoryContract repository;
    public ChangePasswordModel(RepositoryContract repository) {
        this.repository = repository;
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

    @Override
    public void onChangePassword(String currentPasswordText, String newPasswordText,
                                 RepositoryContract.onChangePasswordCallback onChangePasswordCallback) {
        repository.changePassword(currentPasswordText,newPasswordText,onChangePasswordCallback);
    }
}
