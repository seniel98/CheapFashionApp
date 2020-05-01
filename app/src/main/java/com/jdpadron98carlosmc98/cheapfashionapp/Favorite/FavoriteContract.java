package com.jdpadron98carlosmc98.cheapfashionapp.Favorite;

import com.jdpadron98carlosmc98.cheapfashionapp.app.ProductItem;
import com.jdpadron98carlosmc98.cheapfashionapp.app.RepositoryContract;

import java.lang.ref.WeakReference;
import java.util.List;

public interface FavoriteContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void onDataUpdated(FavoriteViewModel viewModel);

        void fillArrayList(FavoriteViewModel viewModel);

        void navigateToNextScreen();

        void goToHome();

        void goToMyProducts();

        void goToProfile();

        void goToLogin();

        void goToDetail();

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

        void selectProductData(ProductItem item);

        void goToHome();

        void goToMyProducts();

        void goToProfile();

        void callLogout();

        List<ProductItem> getListFromModel();
    }

    interface Model {
        String getStoredData();

        void onDataFromNextScreen(String data);

        void onRestartScreen(String data);

        void onDataFromPreviousScreen(String data);

        List<ProductItem> getListFromRepository();

        List<ProductItem> getFavoriteList(List<ProductItem> list);

        void logout(RepositoryContract.OnLogoutCallback logoutCallback);
    }

    interface Router {

        void passStateToNextScreen(FavoriteState state);

        FavoriteState getStateFromPreviousScreen();

        FavoriteState getStateFromNextScreen();

        void passStateToPreviousScreen(FavoriteState state);


    }
}
