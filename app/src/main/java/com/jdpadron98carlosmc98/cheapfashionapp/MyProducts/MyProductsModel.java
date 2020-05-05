package com.jdpadron98carlosmc98.cheapfashionapp.MyProducts;

import com.jdpadron98carlosmc98.cheapfashionapp.data.ProductItem;
import com.jdpadron98carlosmc98.cheapfashionapp.data.RepositoryContract;

import java.util.List;

public class MyProductsModel implements MyProductsContract.Model {

    public static String TAG = MyProductsModel.class.getSimpleName();

    private String data;
    private RepositoryContract repository;

    public MyProductsModel(RepositoryContract repository) {
        this.repository = repository;
    }


    @Override
    public void logout(RepositoryContract.OnLogoutCallback logoutCallback) {
        repository.logout(logoutCallback);
    }

    @Override
    public void getDataFromRepository(RepositoryContract.OnGetMyProductsJSONCallback myProductsJSONCallback, List<ProductItem> myProductsItemList) {
        repository.getMyProductsJSONFromURL(myProductsJSONCallback,myProductsItemList);
    }

    @Override
    public String getStoredData() {
        // Log.e(TAG, "getStoredData()");
        return "data";
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
    public List<ProductItem> getListFromRepository() {
        return repository.getProductList();
    }
}
