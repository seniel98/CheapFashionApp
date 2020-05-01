package com.jdpadron98carlosmc98.cheapfashionapp.app;

import java.util.List;

public interface RepositoryContract {


    void createUser(UserData userData, String password, RepositoryContract.RegisterCallback registerCallback);


    List<ProductItem> getProductList();


    interface RegisterCallback {
        void createUserError(boolean error, String msg);
    }

}
