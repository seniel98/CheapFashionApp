package com.jdpadron98carlosmc98.cheapfashionapp.ProductDetail;

import java.lang.ref.WeakReference;

public interface ProductDetailContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void onDataUpdated(ProductDetailViewModel viewModel);
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
    }

    interface Model {
        String getStoredData();

        void onDataFromNextScreen(String data);

        void onRestartScreen(String data);

        void onDataFromPreviousScreen(String data);
    }

    interface Router {
        void navigateToNextScreen();

        void passStateToNextScreen(ProductDetailState state);

        ProductDetailState getStateFromPreviousScreen();

        ProductDetailState getStateFromNextScreen();

        void passStateToPreviousScreen(ProductDetailState state);
    }
}
