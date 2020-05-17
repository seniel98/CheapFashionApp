package com.jdpadron98carlosmc98.cheapfashionapp.SplashScreen;

import com.jdpadron98carlosmc98.cheapfashionapp.data.ProductItem;
import com.jdpadron98carlosmc98.cheapfashionapp.data.RepositoryContract;

import java.util.List;

public class SplashScreenModel implements SplashScreenContract.Model {

    public static String TAG = SplashScreenModel.class.getSimpleName();

    private RepositoryContract repository;

    public SplashScreenModel(RepositoryContract repository) {

        this.repository = repository;

    }

    @Override
    public void checkSession(RepositoryContract.OnLoggedInCallback loggedInCallback) {
        repository.isLoggedIn(loggedInCallback);
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
