package com.jdpadron98carlosmc98.cheapfashionapp.SplashScreen;

import com.jdpadron98carlosmc98.cheapfashionapp.data.ProductItem;
import com.jdpadron98carlosmc98.cheapfashionapp.data.RepositoryContract;

import java.lang.ref.WeakReference;
import java.util.List;

public interface SplashScreenContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void goToLogin();

        void goToHome();

        void showToast(String msg);
    }

    interface Presenter {

        void goToLogin();

        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void injectRouter(Router router);

        void goToHome();

        void checkSession();
    }

    interface Model {
        void checkSession(RepositoryContract.OnLoggedInCallback loggedInCallback);

        void getDataFromRepository(RepositoryContract.OnGetJSONCallback onGetJSONCallback);

        void insertListInDb(RepositoryContract.onInsertListInDBCallback onInsertListInDBCallback, List<ProductItem> productItems);
    }

    interface Router {

        void passDataToNextScreen(SplashScreenState state);

        SplashScreenState getDataFromPreviousScreen();
    }
}
