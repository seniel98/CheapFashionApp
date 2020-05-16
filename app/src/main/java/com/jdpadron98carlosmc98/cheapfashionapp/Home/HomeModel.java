package com.jdpadron98carlosmc98.cheapfashionapp.Home;

import com.jdpadron98carlosmc98.cheapfashionapp.data.RepositoryContract;

public class HomeModel implements HomeContract.Model {

    public static String TAG = HomeModel.class.getSimpleName();

    private String data;
    private RepositoryContract repository;

    public HomeModel(RepositoryContract repository) {
        this.repository = repository;
    }


    @Override
    public void getProductListData(RepositoryContract.GetProductListCallback getProductListCallback) {
        repository.getProductList(getProductListCallback);
    }

    @Override
    public void getDataFromRepository(RepositoryContract.OnGetJSONCallback getJSONCallback) {
        repository.getJSONFromURL(getJSONCallback);
    }

    @Override
    public void getFavoriteDataFromRepository(RepositoryContract.GetFavoriteJSONCallback getFavoriteJSONCallback) {
        repository.getFavoriteJSONFromURL(getFavoriteJSONCallback);
    }


    public RepositoryContract getRepository() {
        return repository;
    }


    @Override
    public void logout(RepositoryContract.OnLogoutCallback logoutCallback) {
        repository.logout(logoutCallback);
    }

}
