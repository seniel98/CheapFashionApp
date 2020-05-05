package com.jdpadron98carlosmc98.cheapfashionapp.Home;

import android.util.Log;

import com.jdpadron98carlosmc98.cheapfashionapp.app.ProductItem;
import com.jdpadron98carlosmc98.cheapfashionapp.app.RepositoryContract;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class HomePresenter implements HomeContract.Presenter {

    public static String TAG = HomePresenter.class.getSimpleName();

    private WeakReference<HomeContract.View> view;
    private HomeState state;
    private HomeContract.Model model;
    private HomeContract.Router router;

    private List<ProductItem> productItemList;

    public HomePresenter(HomeState state) {
        this.state = state;
    }

    @Override
    public void onStart() {
        // Log.e(TAG, "onStart()");

        // initialize the state if is necessary
        if (state == null) {
            state = new HomeState();
        }

        // use passed state if is necessary
        HomeState savedState = router.getStateFromPreviousScreen();
        if (savedState != null) {

            // update the model if is necessary
            // model.onDataFromPreviousScreen(savedState.data);
        }
      /*  //Correccion para guardar el estado de la lista en el caso de cargar los items para la prueba
        state.homeProductList = getListFromModel();
        view.get().fillArrayList(state);*/
    }

    @Override
    public void onRestart() {
        // Log.e(TAG, "onRestart()");
        // initialize the state if is necessary
        if (state == null) {
            state = new HomeState();
        }

        // use passed state if is necessary
        HomeState savedState = router.getStateFromPreviousScreen();
        if (savedState != null) {

            // update the model if is necessary
            // model.onDataFromPreviousScreen(savedState.data);
        }
      /*  //Correccion para guardar el estado de la lista en el caso de cargar los items para la prueba
        state.homeProductList = getListFromModel();
        view.get().fillArrayList(state);*/
    }

    @Override
    public void onResume() {
        // Log.e(TAG, "onResume()");

        // use passed state if is necessary
        HomeState savedState = router.getStateFromNextScreen();
        if (savedState != null) {

            // update the model if is necessary
            model.onDataFromNextScreen(savedState.data);
        }

        // call the model and update the state
//        state.data = model.getStoredData();

        // update the view


    }


    public void getDataFromRepository() {
        productItemList = new ArrayList<>();
        model.getDataFromRepository(new RepositoryContract.OnGetJSONCallback() {
            @Override
            public void onGetJSON(boolean error) {
                if (!error) {
                    state.homeProductList = productItemList;
                    view.get().fillArrayList(state);
                    view.get().createRecyclerView();
                } else {
                    state.homeProductList = new ArrayList<>();
                    view.get().fillArrayList(state);
                    view.get().createRecyclerView();
                }
            }
        }, productItemList);
    }


    @Override
    public void onBackPressed() {
        // Log.e(TAG, "onBackPressed()");
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
    public void selectProduct(ProductItem item) {
        state.item = item;
        Log.e(TAG, "selectProduct.item" + state.item);
        router.passStateToNextScreen(state);
        view.get().goToDetail();

    }

    @Override
    public List<ProductItem> getListFromModel() {
        return model.getListFromRepository();
    }

    @Override
    public void goToFavorites() {
        view.get().goToFavorites();
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
                if (!error) {
                    view.get().showToast("Logged out successfully!");
                    view.get().goToLogin();
                } else {

                }
            }
        });

    }

    @Override
    public void goToAddProduct() {
        view.get().goAddProduct();
    }

    @Override
    public void injectView(WeakReference<HomeContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(HomeContract.Model model) {
        this.model = model;
    }

    @Override
    public void injectRouter(HomeContract.Router router) {
        this.router = router;
    }
}
