package com.jdpadron98carlosmc98.cheapfashionapp.app;

import java.util.List;

public interface RepositoryContract {


    List<ProductItem> getProductList();

    /**
     *  Cogemos el archivo JSON desde la url de Firebase mediante el uso de la libreria Volley.
     *  Esta forma es mucho mas rapida y eficaz que mediante el uso de Async Task.
     * @param getJSONCallback
     */

   // void getJSONFromURL(OnGetJSONCallback getJSONCallback);

    /**
     * Metodo que registra un usuario en la aplicacion
     *
     * @param userData
     * @param password
     * @param signUpCallback
     */
    void signUp(UserData userData, String password, OnSignUpCallback signUpCallback);


    /**
     * Method that logs in if the user is on the Firebase Authentication dB
     *
     * @param email
     * @param password
     * @param callback
     */
    void signIn(String email, String password, RepositoryContract.OnSignInCallback callback);


    /**
     * Metodo para cerrar la sesion de la apliacion
     *
     * @param logoutCallback
     */
    void logout(RepositoryContract.OnLogoutCallback logoutCallback);

    /**
     * Metodo para comprobar si el usuario tiene la sesion activa
     *
     * @param loggedInCallback
     */
    void isLoggedIn(RepositoryContract.OnLoggedInCallback loggedInCallback);

    interface OnSignInCallback {
        void onSignIn(boolean error);
    }


    interface OnSignUpCallback {
        void onSignUp(boolean error, String msg);
    }

    interface OnLogoutCallback {
        void onLogout(boolean error);
    }

    interface OnLoggedInCallback {
        void onLoggedIn(boolean isLoggedIn);
    }

    interface OnGetJSONCallback {
        void onGetJSON(boolean error);
    }

}
