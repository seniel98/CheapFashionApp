package com.jdpadron98carlosmc98.cheapfashionapp.app;

import java.util.List;

public interface RepositoryContract {


    void createUser(UserData userData, String password, RepositoryContract.RegisterCallback registerCallback);


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



    interface RegisterCallback {
        void createUserError(boolean error, String msg);
    }

}
