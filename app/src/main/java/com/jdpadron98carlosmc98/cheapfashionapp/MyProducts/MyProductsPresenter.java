package com.jdpadron98carlosmc98.cheapfashionapp.MyProducts;

import com.jdpadron98carlosmc98.cheapfashionapp.app.ProductItem;

import java.lang.ref.WeakReference;

public class MyProductsPresenter implements MyProductsContract.Presenter {

    public static String TAG = MyProductsPresenter.class.getSimpleName();

    private WeakReference<MyProductsContract.View> view;
    private MyProductsState state;
    private MyProductsContract.Model model;
    private MyProductsContract.Router router;

    public MyProductsPresenter(MyProductsState state) {
        this.state = state;
    }

    @Override
    public void onStart() {
        // Log.e(TAG, "onStart()");

        // initialize the state if is necessary
        if (state == null) {
            state = new MyProductsState();
        }

        // use passed state if is necessary
        MyProductsState savedState = router.getStateFromPreviousScreen();
        if (savedState != null) {

            // update the model if is necessary
            //model.onDataFromPreviousScreen(savedState.data);
        }
    }

    @Override
    public void onRestart() {
        // Log.e(TAG, "onRestart()");

        // update the model if is necessary
        //model.onRestartScreen(state.data);
    }

    @Override
    public void onResume() {
        // Log.e(TAG, "onResume()");

        // use passed state if is necessary
        MyProductsState savedState = router.getStateFromNextScreen();
        if (savedState != null) {

            // update the model if is necessary
            model.onDataFromNextScreen(savedState.data);
        }

        // call the model and update the state
        //state.data = model.getStoredData();

        // update the view
        view.get().onDataUpdated(state);

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

    }

    @Override
    public void goToFavoritesRouter() {
        router.goToFavorites();
    }

    @Override
    public void goToHomeRouter() {
        router.goToHome();
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
    public void goToAddProduct() {
        router.goAddProduct();
    }

    @Override
    public void injectView(WeakReference<MyProductsContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(MyProductsContract.Model model) {
        this.model = model;
    }

    @Override
    public void injectRouter(MyProductsContract.Router router) {
        this.router = router;
    }
}
