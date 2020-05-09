package com.jdpadron98carlosmc98.cheapfashionapp.MyProducts;

import com.jdpadron98carlosmc98.cheapfashionapp.data.ProductItem;
import com.jdpadron98carlosmc98.cheapfashionapp.data.RepositoryContract;

import java.lang.ref.WeakReference;

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

        void getDataFromRepository();

        void deleteProduct(ProductItem item);
    }

    interface Model {

        void logout(RepositoryContract.OnLogoutCallback logoutCallback);

        void getDataFromRepository(RepositoryContract.OnGetMyProductsCallback myProductsJSONCallback);

        void deleteProduct(ProductItem item,RepositoryContract.DeleteProductCallback deleteProductCallback);
    }

    interface Router {

        void passStateToNextScreen(MyProductsState state);

        MyProductsState getStateFromPreviousScreen();

        MyProductsState getStateFromNextScreen();

        void passStateToPreviousScreen(MyProductsState state);

    }
}
