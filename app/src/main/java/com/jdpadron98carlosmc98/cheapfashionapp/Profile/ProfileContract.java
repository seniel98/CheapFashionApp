package com.jdpadron98carlosmc98.cheapfashionapp.Profile;

import com.jdpadron98carlosmc98.cheapfashionapp.app.RepositoryContract;
import com.jdpadron98carlosmc98.cheapfashionapp.app.UserData;

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

        void updateView(ProfileViewModel viewModel);
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

        void getUserProfileData();

        void onStart();

        void onRestart();
    }

    interface Model {
        void logout(RepositoryContract.OnLogoutCallback logoutCallback);
        //String fetchData();

        void getUserProfileData(UserData userData, RepositoryContract.OnGetUserProfileDataCallback getUserProfileDataCallback);
    }

    interface Router {

        void passDataToNextScreen(ProfileState state);

        ProfileState getDataFromPreviousScreen();
    }
}
