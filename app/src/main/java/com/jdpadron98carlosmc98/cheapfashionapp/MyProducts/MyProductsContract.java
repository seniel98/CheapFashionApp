package com.jdpadron98carlosmc98.cheapfashionapp.MyProducts;

import com.jdpadron98carlosmc98.cheapfashionapp.app.ProductItem;

import java.lang.ref.WeakReference;

public interface MyProductsContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void onDataUpdated(MyProductsViewModel viewModel);
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

        void selectProduct(ProductItem item);
    }

    interface Model {
        String getStoredData();

        void onDataFromNextScreen(String data);

        void onRestartScreen(String data);

        void onDataFromPreviousScreen(String data);
    }

    interface Router {
        void navigateToNextScreen();

        void passStateToNextScreen(MyProductsState state);

        MyProductsState getStateFromPreviousScreen();

        MyProductsState getStateFromNextScreen();

        void passStateToPreviousScreen(MyProductsState state);
    }
}
