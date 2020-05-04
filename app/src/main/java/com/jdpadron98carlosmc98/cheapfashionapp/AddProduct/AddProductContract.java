package com.jdpadron98carlosmc98.cheapfashionapp.AddProduct;

import android.widget.ImageView;

import com.jdpadron98carlosmc98.cheapfashionapp.app.RepositoryContract;

import java.lang.ref.WeakReference;

public interface AddProductContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void onDataUpdated(AddProductViewModel viewModel);

        void onUpdateImage(AddProductViewModel viewModel);

        void navigateToNextScreen();

        void goHome();

        void displayData(AddProductViewModel viewModel);
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void injectRouter(Router router);

        void onResume();

        void onStart();

        void onRestart();

        void onBackPressed();

        void onPause();

        void onDestroy();

        void saveImage(ImageView addProductImage);

        void addNewProduct(String productName, String productPrice, String productDescription, ImageView addProductImage);
    }

    interface Model {
        String getStoredData();

        void onDataFromNextScreen(String data);

        void onRestartScreen(String data);

        void onDataFromPreviousScreen(String data);

        void addProduct(String productName, String productPrice, String productDescription, ImageView addProductImage,RepositoryContract.CreateProductEntryCallBack createProductEntryCallBack);
    }

    interface Router {

        void passStateToNextScreen(AddProductState state);

        AddProductState getStateFromPreviousScreen();

        AddProductState getStateFromNextScreen();

        void passStateToPreviousScreen(AddProductState state);

        void saveState(AddProductState state);

        AddProductState getAddProductState();
    }
}
