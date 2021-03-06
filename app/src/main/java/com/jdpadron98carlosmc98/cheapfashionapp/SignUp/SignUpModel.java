package com.jdpadron98carlosmc98.cheapfashionapp.SignUp;

import com.jdpadron98carlosmc98.cheapfashionapp.data.RepositoryContract;
import com.jdpadron98carlosmc98.cheapfashionapp.data.UserData;

public class SignUpModel implements SignUpContract.Model {

    public static String TAG = SignUpModel.class.getSimpleName();

    private RepositoryContract repository;

    public SignUpModel(RepositoryContract repository) {
        this.repository = repository;
    }


    @Override
    public void signUp(UserData userData, String pass, RepositoryContract.OnSignUpCallback OnSignUpCallback) {
        repository.signUp(userData, pass, OnSignUpCallback);
    }
}
