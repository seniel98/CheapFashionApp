package com.jdpadron98carlosmc98.cheapfashionapp.Favorite;

import android.util.Log;

import com.jdpadron98carlosmc98.cheapfashionapp.data.ProductItem;
import com.jdpadron98carlosmc98.cheapfashionapp.data.RepositoryContract;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class FavoritePresenter implements FavoriteContract.Presenter {

    public static String TAG = FavoritePresenter.class.getSimpleName();

    private WeakReference<FavoriteContract.View> view;
    private FavoriteState state;
    private FavoriteContract.Model model;
    private FavoriteContract.Router router;
    private List<ProductItem> productItems;
    public FavoritePresenter(FavoriteState state) {
        this.state = state;
    }

    @Override
    public void onStart() {
        // Log.e(TAG, "onStart()");

        // initialize the state if is necessary
        if (state == null) {
            state = new FavoriteState();
        }

        // use passed state if is necessary
        FavoriteState savedState = router.getStateFromPreviousScreen();
        if (savedState != null) {

            // update the model if is necessary
            //model.onDataFromPreviousScreen(savedState.data);
        }
    }

    @Override
    public void onRestart() {
        // Log.e(TAG, "onRestart()");
        if (state == null) {
            state = new FavoriteState();
        }

        // use passed state if is necessary
        FavoriteState savedState = router.getStateFromPreviousScreen();
        if (savedState != null) {

            // update the model if is necessary
            //model.onDataFromPreviousScreen(savedState.data);
        }

    }

    @Override
    public void onResume() {
        // Log.e(TAG, "onResume()");
        Log.d(TAG,"Favorite.onResume");
        // use passed state if is necessary
        FavoriteState savedState = router.getStateFromNextScreen();
        if (savedState != null) {

            // update the model if is necessary
            model.onDataFromNextScreen(savedState.data);
        }

    }

    /**
     * Metodo que obtiene los productos de favoritos de la base de datos local y la inserta en el
     * adapter.
     */

    private void getProductListData() {
        model.getFavoriteListData(new RepositoryContract.GetFavoriteListCallback() {
            @Override
            public void setFavoriteList(List<ProductItem> favoriteItems) {
                state.favoriteItems = favoriteItems;
                //Log.e(TAG, "getProductListData" + loadProducts.get(0).userData);
                view.get().fillArrayList(state);
            }

        });
    }

    /**
     * Metodo que obtiene los productos favoritos del usuario desde el Json de firebaseDatabase
     */
    public void downloadDataFromRepository() {
        model.getDataFromRepository(new RepositoryContract.GetFavoriteJSONCallback() {
            @Override
            public void onGetFavoriteJSONCallback(boolean error) {
                if (error) {
                    getProductListData();
                    view.get().showToast("NO CONNECTION. DATA MAY BE OBSOLETE");
                }else{
                    getProductListData();
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        // Log.e(TAG, "onBackPressed()");
        view.get().goToHome();
    }

    @Override
    public void onPause() {
        // Log.e(TAG, "onPause()");
    }

    @Override
    public void onDestroy() {
        // Log.e(TAG, "onDestroy()");
    }

    @Override
    public void selectProductData(ProductItem item) {
        state.item = item;
        router.passStateToNextScreen(state);
        view.get().goToDetail();
    }

    @Override
    public void goToHome() {
        view.get().goToHome();
    }

    @Override
    public void goToMyProducts() {
        view.get().goToMyProducts();
    }

    @Override
    public void goToProfile() {
        view.get().goToProfile();
    }

    @Override
    public void callLogout() {
        model.logout(new RepositoryContract.OnLogoutCallback() {
            @Override
            public void onLogout(boolean error) {
                if(!error){
                    view.get().showToast("Logged out successfully!");
                    view.get().goToLogin();
                }else{

                }
            }
        });
    }

    @Override
    public void injectView(WeakReference<FavoriteContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(FavoriteContract.Model model) {
        this.model = model;
    }

    @Override
    public void injectRouter(FavoriteContract.Router router) {
        this.router = router;
    }
}
