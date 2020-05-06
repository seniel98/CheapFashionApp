package com.jdpadron98carlosmc98.cheapfashionapp.MyProducts;

import com.jdpadron98carlosmc98.cheapfashionapp.data.RepositoryContract;

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
    public void getDataFromRepository(RepositoryContract.OnGetMyProductsCallback myProductsJSONCallback) {
        repository.getMyProductsFromDatabase(myProductsJSONCallback);
    }

}
