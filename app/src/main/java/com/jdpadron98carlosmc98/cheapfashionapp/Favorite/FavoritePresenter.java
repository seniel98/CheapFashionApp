package com.jdpadron98carlosmc98.cheapfashionapp.Favorite;

import android.util.Log;

import com.jdpadron98carlosmc98.cheapfashionapp.app.ProductItem;
import com.jdpadron98carlosmc98.cheapfashionapp.app.RepositoryContract;

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
        //Correccion para guardar el estado de la lista en el caso de cargar los items para la prueba
//        List<ProductItem> list = getListFromModel();
//        List<ProductItem> favoriteList = model.getFavoriteList(list);
//        state.productItems = favoriteList;
//        view.get().fillArrayList(state);
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
        //Correccion para guardar el estado de la lista en el caso de cargar los items para la prueba
//        List<ProductItem> list = getListFromModel();
//        List<ProductItem> favoriteList = model.getFavoriteList(list);
//        state.productItems = favoriteList;
//        view.get().fillArrayList(state);
        // update the model if is necessary
        //model.onRestartScreen(state.data);
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

        getDataFromRepository();
        // call the model and update the state
//        state.data = model.getStoredData();

        // update the view
//        List<ProductItem> favoriteList = model.getFavoriteList(state.productItems);
//        state.productItems = favoriteList;
//        view.get().fillArrayList(state);


    }
    public void getDataFromRepository() {
        productItems = new ArrayList<>();
        model.getDataFromRepository(new RepositoryContract.GetFavoriteJSONCallback() {
            @Override
            public void onGetFavoriteJSONCallback(boolean error) {
                if (!error) {
                    state.productItems = productItems;
                    view.get().fillArrayList(state);
                    view.get().createRecyclerView();
                } else {
                    state.productItems = new ArrayList<>();
                    view.get().fillArrayList(state);
                    view.get().createRecyclerView();
                }
            }
        }, productItems);
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
    public List<ProductItem> getListFromModel() {
        return model.getListFromRepository();
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
