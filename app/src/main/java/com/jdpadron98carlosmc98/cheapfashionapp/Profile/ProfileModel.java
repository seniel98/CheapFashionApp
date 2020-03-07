package com.jdpadron98carlosmc98.cheapfashionapp.Profile;

import com.jdpadron98carlosmc98.cheapfashionapp.app.RepositoryContract;

public class ProfileModel implements ProfileContract.Model {

    public static String TAG = ProfileModel.class.getSimpleName();

    private RepositoryContract repository;

    public ProfileModel(RepositoryContract repository) {
        this.repository = repository;
    }

/*
    @Override
    public String fetchData() {
        // Log.e(TAG, "fetchData()");
        return "Hello";
    }
    */
}
