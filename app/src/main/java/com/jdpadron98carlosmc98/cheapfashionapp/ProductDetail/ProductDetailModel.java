package com.jdpadron98carlosmc98.cheapfashionapp.ProductDetail;

import com.jdpadron98carlosmc98.cheapfashionapp.data.ProductItem;
import com.jdpadron98carlosmc98.cheapfashionapp.data.RepositoryContract;

public class ProductDetailModel implements ProductDetailContract.Model {

    public static String TAG = ProductDetailModel.class.getSimpleName();

    private String data;
    private RepositoryContract repository;

    public ProductDetailModel(RepositoryContract repository) {
        this.repository = repository;
    }


    @Override
    public void checkIfProductFavorite(String pid, RepositoryContract.IsFavoriteCallback isFavoriteCallback) {
        repository.checkIfIsFavorite(pid, isFavoriteCallback);
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
    public void addProductToFavorite(ProductItem item, RepositoryContract.CreateFavoriteProductEntryCallBack createFavoriteProductEntryCallBack) {
        repository.addFavoriteProduct(item, createFavoriteProductEntryCallBack);
    }
}
