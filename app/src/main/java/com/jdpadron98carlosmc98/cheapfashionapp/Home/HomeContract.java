package com.jdpadron98carlosmc98.cheapfashionapp.Home;

import com.jdpadron98carlosmc98.cheapfashionapp.app.ProductItem;
import com.jdpadron98carlosmc98.cheapfashionapp.app.RepositoryContract;

import java.lang.ref.WeakReference;
import java.util.List;

public interface HomeContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void onDataUpdated(HomeViewModel viewModel);

        void fillArrayList(HomeViewModel viewModel);

        void goToFavorites();

        void goToMyProducts();

        void goToProfile();

        void goToLogin();

        void goAddProduct();

        void goToDetail();

        void navigateToNextScreen();

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

        void goToMyProducts();

        void goToProfile();

        void callLogout();

        void goToAddProduct();

        List<ProductItem> getListFromModel();
    }

    interface Model {
        String getStoredData();

        void onDataFromNextScreen(String data);

        void onRestartScreen(String data);

        List<ProductItem> getListFromRepository();

        void onDataFromPreviousScreen(String data);

        void logout(RepositoryContract.OnLogoutCallback logoutCallback);
    }

    interface Router {

        void passStateToNextScreen(HomeState state);

        HomeState getStateFromPreviousScreen();

        HomeState getStateFromNextScreen();

        void passStateToPreviousScreen(HomeState state);


    }
}
