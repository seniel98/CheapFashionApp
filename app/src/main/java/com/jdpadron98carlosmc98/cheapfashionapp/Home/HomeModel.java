package com.jdpadron98carlosmc98.cheapfashionapp.Home;

import com.jdpadron98carlosmc98.cheapfashionapp.data.ProductItem;
import com.jdpadron98carlosmc98.cheapfashionapp.data.RepositoryContract;

import java.util.List;

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

    @Override
    public void insertListInDb(RepositoryContract.onInsertListInDBCallback onInsertListInDBCallback, List<ProductItem> productItems) {
        repository.insertListInDB(productItems, onInsertListInDBCallback);
    }


    public RepositoryContract getRepository() {
        return repository;
    }


    @Override
    public void logout(RepositoryContract.OnLogoutCallback logoutCallback) {
        repository.logout(logoutCallback);
    }

}
