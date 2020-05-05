package com.jdpadron98carlosmc98.cheapfashionapp.AddProduct;

import android.widget.ImageView;

import com.jdpadron98carlosmc98.cheapfashionapp.data.RepositoryContract;

public class AddProductModel implements AddProductContract.Model {

    public static String TAG = AddProductModel.class.getSimpleName();

    private String data;
    private RepositoryContract repository;

    public AddProductModel(RepositoryContract repository) {
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
    public void addProduct(String productName, String productPrice, String productDescription, ImageView imageView, RepositoryContract.CreateProductEntryCallBack createProductEntryCallBack) {
        repository.addNewProduct(productName,productPrice,productDescription, imageView, createProductEntryCallBack);
    }
}
