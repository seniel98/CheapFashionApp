package com.jdpadron98carlosmc98.cheapfashionapp.SignUp;

import com.jdpadron98carlosmc98.cheapfashionapp.app.RepositoryContract;

public class SignUpModel implements SignUpContract.Model {

    public static String TAG = SignUpModel.class.getSimpleName();

    private RepositoryContract repository;

    public SignUpModel(RepositoryContract repository) {
        this.repository = repository;
    }

  /*  @Override
    public String fetchData() {
        // Log.e(TAG, "fetchData()");
        return "Hello";
    }*/
}
