package com.jdpadron98carlosmc98.cheapfashionapp.Login;

import com.jdpadron98carlosmc98.cheapfashionapp.data.ProductItem;
import com.jdpadron98carlosmc98.cheapfashionapp.data.RepositoryContract;

import java.util.List;

public class LoginModel implements LoginContract.Model {

    public static String TAG = LoginModel.class.getSimpleName();

    private RepositoryContract repository;

    public LoginModel(RepositoryContract repository) {
        this.repository = repository;
    }

    @Override
    public void signIn(String emailStr, String passStr, RepositoryContract.OnSignInCallback onSignInCallback) {
        repository.signIn(emailStr, passStr, onSignInCallback);
    }

    @Override
    public void getDataFromRepository(RepositoryContract.OnGetJSONCallback onGetJSONCallback) {
        repository.getJSONFromURL(onGetJSONCallback);
    }

    @Override
    public void insertListInDb(RepositoryContract.onInsertListInDBCallback onInsertListInDBCallback, List<ProductItem> productItems) {
        repository.insertListInDB(productItems, onInsertListInDBCallback);
    }
}
