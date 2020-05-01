package com.jdpadron98carlosmc98.cheapfashionapp.MyProducts;

import com.jdpadron98carlosmc98.cheapfashionapp.app.ProductItem;
import com.jdpadron98carlosmc98.cheapfashionapp.app.RepositoryContract;

import java.lang.ref.WeakReference;
import java.util.List;

public interface MyProductsContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void onDataUpdated(MyProductsViewModel viewModel);

        void fillArrayList(MyProductsViewModel viewModel);

        void goToFavorites();

        void goToHome();

        void goToProfile();

        void goToLogin();

        void goAddProduct();

        void showToast(String msg);
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

        void goToFavorites();

        void goToHome();

        void goToProfile();

        void callLogout();

        void goToAddProduct();

        List<ProductItem> getListFromModel();
    }

    interface Model {
        String getStoredData();

        void onDataFromNextScreen(String data);

        void onRestartScreen(String data);

        void onDataFromPreviousScreen(String data);

        List<ProductItem> getListFromRepository();

        void logout(RepositoryContract.OnLogoutCallback logoutCallback);
    }

    interface Router {

        void passStateToNextScreen(MyProductsState state);

        MyProductsState getStateFromPreviousScreen();

        MyProductsState getStateFromNextScreen();

        void passStateToPreviousScreen(MyProductsState state);

    }
}
