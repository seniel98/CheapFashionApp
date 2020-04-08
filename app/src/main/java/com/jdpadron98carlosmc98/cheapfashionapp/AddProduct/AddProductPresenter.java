package com.jdpadron98carlosmc98.cheapfashionapp.AddProduct;

import android.util.Log;

import java.lang.ref.WeakReference;

public class AddProductPresenter implements AddProductContract.Presenter {

    public static String TAG = AddProductPresenter.class.getSimpleName();

    private WeakReference<AddProductContract.View> view;
    private AddProductState state;
    private AddProductContract.Model model;
    private AddProductContract.Router router;

    public AddProductPresenter(AddProductState state) {
        this.state = state;
    }

    @Override
    public void onStart() {
        // Log.e(TAG, "onStart()");

        // initialize the state if is necessary
        if (state == null) {
            state = new AddProductState();
        }

        // use passed state if is necessary
        AddProductState savedState = router.getStateFromPreviousScreen();
        if (savedState != null) {

            // update the model if is necessary
//            model.onDataFromPreviousScreen(savedState.data);
        }
    }

    @Override
    public void onRestart() {
        // Log.e(TAG, "onRestart()");

        // update the model if is necessary
//        model.onRestartScreen(state.data);
    }

    @Override
    public void onResume() {
        // Log.e(TAG, "onResume()");

        // use passed state if is necessary
        AddProductState savedState = router.getStateFromNextScreen();
        if (savedState != null) {

            // update the model if is necessary
//            model.onDataFromNextScreen(savedState.data);
        }

        // call the model and update the state
//        state.data = model.getStoredData();

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
    public void injectView(WeakReference<AddProductContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(AddProductContract.Model model) {
        this.model = model;
    }

    @Override
    public void injectRouter(AddProductContract.Router router) {
        this.router = router;
    }
}
