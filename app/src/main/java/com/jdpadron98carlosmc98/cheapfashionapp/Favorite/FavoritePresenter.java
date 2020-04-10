package com.jdpadron98carlosmc98.cheapfashionapp.Favorite;

import com.jdpadron98carlosmc98.cheapfashionapp.app.ProductItem;

import java.lang.ref.WeakReference;
import java.util.List;

public class FavoritePresenter implements FavoriteContract.Presenter {

    public static String TAG = FavoritePresenter.class.getSimpleName();

    private WeakReference<FavoriteContract.View> view;
    private FavoriteState state;
    private FavoriteContract.Model model;
    private FavoriteContract.Router router;

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
        List<ProductItem> list = getListFromModel();
        List<ProductItem> favoriteList = model.getFavoriteList(list);
        state.productItems = favoriteList;
        view.get().fillArrayList(state);
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
        List<ProductItem> list = getListFromModel();
        List<ProductItem> favoriteList = model.getFavoriteList(list);
        state.productItems = favoriteList;
        view.get().fillArrayList(state);
        // update the model if is necessary
        //model.onRestartScreen(state.data);
    }

    @Override
    public void onResume() {
        // Log.e(TAG, "onResume()");

        // use passed state if is necessary
        FavoriteState savedState = router.getStateFromNextScreen();
        if (savedState != null) {

            // update the model if is necessary
            model.onDataFromNextScreen(savedState.data);
        }

        // call the model and update the state
//        state.data = model.getStoredData();

        // update the view
        List<ProductItem> favoriteList = model.getFavoriteList(state.productItems);
        state.productItems = favoriteList;
        view.get().fillArrayList(state);

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
    public void selectProductData(ProductItem item) {
        state.item = item;
        router.passStateToNextScreen(state);
        router.goToDetail();
    }

    @Override
    public void goToHomeRouter() {
        router.goToHome();
    }

    @Override
    public void goToMyProductsRouter() {
        router.goToMyProducts();
    }

    @Override
    public void goToProfileRouter() {
        router.goToProfile();
    }

    @Override
    public void callLogout() {
        router.goToLogin();
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
