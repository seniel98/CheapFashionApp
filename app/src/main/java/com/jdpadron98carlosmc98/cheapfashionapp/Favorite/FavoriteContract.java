package com.jdpadron98carlosmc98.cheapfashionapp.Favorite;

import com.jdpadron98carlosmc98.cheapfashionapp.app.ProductItem;

import java.lang.ref.WeakReference;
import java.util.List;

public interface FavoriteContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void onDataUpdated(FavoriteViewModel viewModel);

        void fillArrayList(FavoriteViewModel viewModel);

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

        void goToHomeRouter();

        void goToMyProductsRouter();

        void goToProfileRouter();

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
    }

    interface Router {
        void navigateToNextScreen();

        void passStateToNextScreen(FavoriteState state);

        FavoriteState getStateFromPreviousScreen();

        FavoriteState getStateFromNextScreen();

        void passStateToPreviousScreen(FavoriteState state);

        void goToHome();

        void goToMyProducts();

        void goToProfile();

        void goToLogin();

        void goToDetail();
    }
}
