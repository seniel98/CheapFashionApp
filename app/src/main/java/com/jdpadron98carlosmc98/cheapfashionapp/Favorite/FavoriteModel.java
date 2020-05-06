package com.jdpadron98carlosmc98.cheapfashionapp.Favorite;

import com.jdpadron98carlosmc98.cheapfashionapp.data.ProductItem;
import com.jdpadron98carlosmc98.cheapfashionapp.data.RepositoryContract;

import java.util.ArrayList;
import java.util.List;

public class FavoriteModel implements FavoriteContract.Model {

    public static String TAG = FavoriteModel.class.getSimpleName();

    private String data;

    private RepositoryContract repository;

    public FavoriteModel(RepositoryContract repository) {
        this.repository = repository;
    }


    @Override
    public void logout(RepositoryContract.OnLogoutCallback logoutCallback) {
        repository.logout(logoutCallback);
    }

    @Override
    public void getDataFromRepository(RepositoryContract.GetFavoriteJSONCallback getFavoriteJSONCallback) {
        repository.getFavoriteJSONFromURL(getFavoriteJSONCallback);
    }

    @Override
    public void getFavoriteListData(RepositoryContract.GetFavoriteListCallback getFavoriteListCallback) {
        repository.getFavoriteList(getFavoriteListCallback);
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
    public List<ProductItem> getFavoriteList(List<ProductItem> list) {
        List<ProductItem> favoriteList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            favoriteList.add(list.get(i));

        }
        return favoriteList;
    }

    @Override
    public List<ProductItem> getListFromRepository() {
        return repository.getProductList();
    }

}
