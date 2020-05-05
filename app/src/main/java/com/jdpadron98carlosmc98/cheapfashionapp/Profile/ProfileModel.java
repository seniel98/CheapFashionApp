package com.jdpadron98carlosmc98.cheapfashionapp.Profile;

import com.jdpadron98carlosmc98.cheapfashionapp.data.RepositoryContract;
import com.jdpadron98carlosmc98.cheapfashionapp.data.UserData;

public class ProfileModel implements ProfileContract.Model {

    public static String TAG = ProfileModel.class.getSimpleName();

    private RepositoryContract repository;

    public ProfileModel(RepositoryContract repository) {
        this.repository = repository;
    }

    @Override
    public void logout(RepositoryContract.OnLogoutCallback logoutCallback) {
        repository.logout(logoutCallback);
    }

    @Override
    public void getUserProfileData(UserData userData, RepositoryContract.OnGetUserProfileDataCallback getUserProfileDataCallback) {
        repository.getUserProfileData(userData, getUserProfileDataCallback);
    }


    /*
    @Override
    public String fetchData() {
        // Log.e(TAG, "fetchData()");
        return "Hello";
    }
    */
}
