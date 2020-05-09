package com.jdpadron98carlosmc98.cheapfashionapp.data;

import android.widget.ImageView;

import java.util.List;

public interface RepositoryContract {

    /**
     * Cogemos el archivo JSON desde la url de Firebase mediante el uso de la libreria Volley.
     * Esta forma es mucho mas rapida y eficaz que mediante el uso de Async Task.
     *
     * @param getJSONCallback
     */

    void getJSONFromURL(OnGetJSONCallback getJSONCallback);

    /**
     * Cogemos el archivo JSON de mis productos desde la url de Firebase mediante el uso de la libreria Volley.
     * Esta forma es mucho mas rapida y eficaz que mediante el uso de Async Task.
     *
     * @param getMyProductsJSONCallback
     */
    void getMyProductsFromDatabase(OnGetMyProductsCallback getMyProductsJSONCallback);

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


    /**
     * Method that add the data values to the database and storage the image into firebase Storage
     *
     * @param productName
     * @param productPrice
     * @param productDescription
     * @param imageView
     * @param callback
     */
    void addNewProduct(final String productName, final String productPrice, final String productDescription, final ImageView imageView, final RepositoryContract.CreateProductEntryCallBack callback);

    void forgetPassword(String email, RepositoryContract.onForgetPasswordCallback callback);

    /**
     * Method that change current password with a new one
     *
     * @param currentPassword
     * @param newPassword
     * @param callback
     */
    void changePassword(String currentPassword, String newPassword, RepositoryContract.onChangePasswordCallback callback);

    /**
     * Metodo para coger los datos del perfil del usuario.
     *
     * @param userData
     * @param getUserProfileDataCallback
     */
    void getUserProfileData(final UserData userData, final OnGetUserProfileDataCallback getUserProfileDataCallback);


    /**
     * Method that add the data values to the database and storage the image into firebase Storage
     *
     * @param productItem
     * @param callback
     */
    void addFavoriteProduct(ProductItem productItem, final RepositoryContract.CreateFavoriteProductEntryCallBack callback);

    void getFavoriteJSONFromURL(final GetFavoriteJSONCallback getFavoriteJSONCallback);

    /**
     * Metodo para pillar la lista de productos de la base de datos en Room
     *
     * @param callback
     */
    void getProductList(final GetProductListCallback callback);

    void getFavoriteList(final GetFavoriteListCallback callback);

    /**
     * Metodo que compueba si el prodcuto está en favoritos con el pid.
     * @param productID
     * @param isFavoriteCallback
     */
    void checkIfIsFavorite(final String productID, final IsFavoriteCallback isFavoriteCallback);

    void deleteProduct(ProductItem item, DeleteProductCallback deleteProductCallback);

    interface DeleteProductCallback{
        void onDelete(boolean error, List<ProductItem> productItems);
    }

    interface IsFavoriteCallback {
        void isFavorite(boolean isFavorite);
    }


    interface GetFavoriteListCallback {
        void setFavoriteList(List<ProductItem> favoriteItems);
    }

    interface GetProductListCallback {
        void setProductList(List<ProductItem> loadProducts);
    }


    interface GetFavoriteJSONCallback {
        void onGetFavoriteJSONCallback(boolean error);
    }

    interface CreateFavoriteProductEntryCallBack {
        void onAddFavoriteProduct(boolean error);
    }

    interface onChangePasswordCallback {
        void onChangePassword(boolean error, String message);
    }

    interface onForgetPasswordCallback {
        void onForgetPassword(boolean error);
    }

    interface CreateProductEntryCallBack {
        void onAddNewProduct(boolean error);
    }


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

    interface OnGetMyProductsCallback {
        void setProductList(List<ProductItem> loadProducts);
    }

    interface OnGetUserProfileDataCallback {
        void onGetProfileData(boolean error);
    }

}
