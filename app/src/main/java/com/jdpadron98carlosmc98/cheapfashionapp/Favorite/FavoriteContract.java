package com.jdpadron98carlosmc98.cheapfashionapp.Favorite;

import com.jdpadron98carlosmc98.cheapfashionapp.app.ProductItem;

import java.lang.ref.WeakReference;

public interface FavoriteContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void onDataUpdated(FavoriteViewModel viewModel);

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
    }

    interface Model {
        String getStoredData();

        void onDataFromNextScreen(String data);

        void onRestartScreen(String data);

        void onDataFromPreviousScreen(String data);
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

    }
}
