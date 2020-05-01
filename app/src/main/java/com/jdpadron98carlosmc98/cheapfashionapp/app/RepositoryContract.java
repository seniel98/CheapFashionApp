package com.jdpadron98carlosmc98.cheapfashionapp.app;

import java.util.List;

public interface RepositoryContract {


    List<ProductItem> getProductList();

    /**
     * Method that logs in if the user is on the Firebase Authentication dB
     *
     * @param email
     * @param password
     * @param callback
     */
    void signIn(String email, String password, RepositoryContract.OnSignInCallback callback);

    interface OnSignInCallback {
        void onSignIn(boolean error);
    }


}
