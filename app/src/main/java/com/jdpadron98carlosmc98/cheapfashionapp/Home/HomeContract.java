package com.jdpadron98carlosmc98.cheapfashionapp.Home;

import com.jdpadron98carlosmc98.cheapfashionapp.data.ProductItem;
import com.jdpadron98carlosmc98.cheapfashionapp.data.RepositoryContract;

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

        void downloadDataFromRepository();

        void getProductListData();
    }

    interface Model {
        void logout(RepositoryContract.OnLogoutCallback logoutCallback);

        void getProductListData(RepositoryContract.GetProductListCallback getProductListCallback);

        void getDataFromRepository(RepositoryContract.OnGetJSONCallback getJSONCallback);
    }

    interface Router {

        void passStateToNextScreen(HomeState state);

        HomeState getStateFromPreviousScreen();

        HomeState getStateFromNextScreen();

        void passStateToPreviousScreen(HomeState state);


    }
}
