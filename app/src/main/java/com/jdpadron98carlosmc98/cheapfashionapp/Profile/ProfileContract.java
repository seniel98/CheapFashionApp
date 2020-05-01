package com.jdpadron98carlosmc98.cheapfashionapp.Profile;

import com.jdpadron98carlosmc98.cheapfashionapp.app.RepositoryContract;

import java.lang.ref.WeakReference;

public interface ProfileContract {

    interface View {
        void injectPresenter(Presenter presenter);

        //void displayData(ProfileViewModel viewModel);
        void goToFavorites();

        void goToMyProducts();

        void goToHome();

        void goToLogin();

        void goChangePass();

        void showToast(String msg);
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void injectRouter(Router router);

        //void fetchData();

        void goToFavorites();

        void goToMyProducts();

        void goToHome();

        void callLogout();

        void goToChangePass();

        void onBackPressed();
    }

    interface Model {
        void logout(RepositoryContract.OnLogoutCallback logoutCallback);
        //String fetchData();
    }

    interface Router {

        void passDataToNextScreen(ProfileState state);

        ProfileState getDataFromPreviousScreen();
    }
}
