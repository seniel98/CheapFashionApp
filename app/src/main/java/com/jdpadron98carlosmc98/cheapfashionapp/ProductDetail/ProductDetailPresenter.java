package com.jdpadron98carlosmc98.cheapfashionapp.ProductDetail;

import android.util.Log;

import com.jdpadron98carlosmc98.cheapfashionapp.app.ProductItem;

import java.lang.ref.WeakReference;

public class ProductDetailPresenter implements ProductDetailContract.Presenter {

    public static String TAG = ProductDetailPresenter.class.getSimpleName();

    private WeakReference<ProductDetailContract.View> view;
    private ProductDetailState state;
    private ProductDetailContract.Model model;
    private ProductDetailContract.Router router;

    public ProductDetailPresenter(ProductDetailState state) {
        this.state = state;
    }

    @Override
    public void onStart() {
        // Log.e(TAG, "onStart()");

        // initialize the state if is necessary
        if (state == null) {
            state = new ProductDetailState();
        }
        ProductDetailState savedState = router.getStateFromPreviousScreen();
        state = savedState;
        // use passed state if is necessary
        if (savedState != null) {
            view.get().displayProductData(savedState);
            // update the model if is necessary
           // model.onDataFromPreviousScreen(savedState.data);
        }
    }

    @Override
    public void onRestart() {
        // Log.e(TAG, "onRestart()");
        view.get().displayProductData(state);

        // update the model if is necessary
        //model.onRestartScreen(state.data);
    }

    @Override
    public void onResume() {
        // Log.e(TAG, "onResume()");

        // use passed state if is necessary
        ProductDetailState savedState = router.getStateFromNextScreen();
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
    public void initDialog() {
        view.get().selectContact(state);
    }

    @Override
    public void likeButtonPressed() {
        ProductItem item = state.item;
        if(state.item.liked){
            view.get().setLikedButtonDisabled();
            item.liked = false;
        }else if(!state.item.liked){
            view.get().setLikeButtonEnabled();
            item.liked = true;
        }
        state.item = item;
    }

    @Override
    public void injectView(WeakReference<ProductDetailContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(ProductDetailContract.Model model) {
        this.model = model;
    }

    @Override
    public void injectRouter(ProductDetailContract.Router router) {
        this.router = router;
    }
}
