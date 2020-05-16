package com.jdpadron98carlosmc98.cheapfashionapp.Profile;

import com.jdpadron98carlosmc98.cheapfashionapp.data.RepositoryContract;
import com.jdpadron98carlosmc98.cheapfashionapp.data.UserData;

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

        void getUserProfileData(UserData userData);

        void onStart();

        void onRestart();

        void changeUserData(String name, String phone);
    }

    interface Model {
        void logout(RepositoryContract.OnLogoutCallback logoutCallback);
        //String fetchData();

        void getUserProfileData(UserData userData, RepositoryContract.OnGetUserProfileDataCallback getUserProfileDataCallback);

        void changeUserData(String name, String phone, RepositoryContract.ChangeUserDataCallback changeUserDataCallback);
    }

    interface Router {

        void passDataToNextScreen(ProfileState state);

        ProfileState getDataFromPreviousScreen();
    }
}
