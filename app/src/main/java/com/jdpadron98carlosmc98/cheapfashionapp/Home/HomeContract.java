package com.jdpadron98carlosmc98.cheapfashionapp.Home;

import com.jdpadron98carlosmc98.cheapfashionapp.app.ProductItem;

import java.lang.ref.WeakReference;
import java.util.List;

public interface HomeContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void onDataUpdated(HomeViewModel viewModel);

        void fillArrayList(HomeViewModel viewModel);
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

        void goToFavoritesRouter();

        void goToMyProductsRouter();

        void goToProfileRouter();

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
    }

    interface Router {
        void navigateToNextScreen();

        void passStateToNextScreen(HomeState state);

        HomeState getStateFromPreviousScreen();

        HomeState getStateFromNextScreen();

        void passStateToPreviousScreen(HomeState state);

        void goToFavorites();

        void goToMyProducts();

        void goToProfile();

        void goToLogin();

        void goAddProduct();

        void goToDetail();
    }
}
