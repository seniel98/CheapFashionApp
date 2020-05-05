package com.jdpadron98carlosmc98.cheapfashionapp.AddProduct;

import android.widget.ImageView;

import com.jdpadron98carlosmc98.cheapfashionapp.data.RepositoryContract;

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
        AddProductState savedState = router.getAddProductState();
        if (savedState != null) {

            // update the model if is necessary
//            model.onDataFromPreviousScreen(savedState.data);
        }
    }

    @Override
    public void onRestart() {
        // Log.e(TAG, "onRestart()");
        AddProductState savedState = router.getAddProductState();
        state = savedState;
        if (savedState != null) {

            // update the model if is necessary
            view.get().onUpdateImage(state);

//            model.onDataFromPreviousScreen(savedState.data);
        }
//        model.onRestartScreen(state.data);

    }

    @Override
    public void onResume() {
        // Log.e(TAG, "onResume()");

        // use passed state if is necessary
        AddProductState savedState = router.getAddProductState();
        if (savedState != null) {
            state.image = savedState.image;
            // update the model if is necessary
//            model.onDataFromNextScreen(savedState.data);
        }

        // call the model and update the state

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
    public void saveImage(ImageView addProductImage) {
        state.image = addProductImage;
        router.saveState(state);
    }

    @Override
    public void addNewProduct(String productName, String productPrice, String productDescription, ImageView addProductImage) {
            model.addProduct(productName,productPrice,productDescription,addProductImage, new RepositoryContract.CreateProductEntryCallBack() {
            @Override
            public void onAddNewProduct(boolean error) {
                if(!error){
                    state.message="Book Added";
                    view.get().displayData(state);
                    view.get().goHome();
                }
            }
        });
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
