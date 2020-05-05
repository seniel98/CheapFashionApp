package com.jdpadron98carlosmc98.cheapfashionapp.ProductDetail;

import com.jdpadron98carlosmc98.cheapfashionapp.data.ProductItem;
import com.jdpadron98carlosmc98.cheapfashionapp.data.RepositoryContract;

import java.lang.ref.WeakReference;

public interface ProductDetailContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void onDataUpdated(ProductDetailViewModel viewModel);

        void displayProductData(ProductDetailState state);

        void sendEmail(ProductDetailViewModel viewModel);

        void callUser(ProductDetailViewModel viewModel);

        void selectContact(ProductDetailViewModel viewModel);

        void setLikedButtonDisabled();

        void setLikeButtonEnabled();
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

        void initDialog();

        void likeButtonPressed();
    }

    interface Model {
        String getStoredData();

        void onDataFromNextScreen(String data);

        void onRestartScreen(String data);

        void onDataFromPreviousScreen(String data);

        void addProductToFavorite(ProductItem item, RepositoryContract.CreateFavoriteProductEntryCallBack createFavoriteProductEntryCallBack);
    }

    interface Router {

        void passStateToNextScreen(ProductDetailState state);

        ProductDetailState getStateFromPreviousScreen();

        ProductDetailState getStateFromNextScreen();

        void passStateToPreviousScreen(ProductDetailState state);
    }
}
