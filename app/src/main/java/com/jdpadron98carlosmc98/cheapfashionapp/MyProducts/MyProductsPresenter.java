package com.jdpadron98carlosmc98.cheapfashionapp.MyProducts;

import com.jdpadron98carlosmc98.cheapfashionapp.app.ProductItem;

import java.lang.ref.WeakReference;
import java.util.List;

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
        //Correccion para guardar el estado de la lista en el caso de cargar los items para la prueba
        List<ProductItem> list = getListFromModel();
        state.list = list;
        view.get().fillArrayList(state);
    }

    @Override
    public void onRestart() {
        // Log.e(TAG, "onRestart()");
        if (state == null) {
            state = new MyProductsState();
        }

        // use passed state if is necessary
        MyProductsState savedState = router.getStateFromPreviousScreen();
        if (savedState != null) {

            // update the model if is necessary
            //model.onDataFromPreviousScreen(savedState.data);
        }
        //Correccion para guardar el estado de la lista en el caso de cargar los items para la prueba
        List<ProductItem> list = getListFromModel();
        state.list = list;
        view.get().fillArrayList(state);
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
    public void selectProduct(ProductItem item) {

    }

    @Override
    public void goToFavorites() {
        view.get().goToFavorites();
    }

    @Override
    public void goToHome() {
        view.get().goToHome();
    }

    @Override
    public void goToProfile() {
        view.get().goToProfile();
    }

    @Override
    public void callLogout() {
        view.get().goToLogin();
    }

    @Override
    public void goToAddProduct() {
        view.get().goAddProduct();
    }

    @Override
    public List<ProductItem> getListFromModel() {
        return model.getListFromRepository();
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
