package com.jdpadron98carlosmc98.cheapfashionapp.Home;

import com.jdpadron98carlosmc98.cheapfashionapp.app.ProductItem;
import com.jdpadron98carlosmc98.cheapfashionapp.app.RepositoryContract;

import java.util.List;

public class HomeModel implements HomeContract.Model {

    public static String TAG = HomeModel.class.getSimpleName();

    private String data;
    private RepositoryContract repository;

    public HomeModel(RepositoryContract repository) {
        this.repository = repository;
    }


    @Override
    public void getDataFromRepository(RepositoryContract.OnGetJSONCallback getJSONCallback, List<ProductItem> productItemList) {
        repository.getJSONFromURL(getJSONCallback, productItemList);
    }

    public List<ProductItem> getListFromRepository() {
        return repository.getProductList();
    }


    @Override
    public void logout(RepositoryContract.OnLogoutCallback logoutCallback) {
        repository.logout(logoutCallback);
    }

    @Override
    public String getStoredData() {
        // Log.e(TAG, "getStoredData()");
        return "null";
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
