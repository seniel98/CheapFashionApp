package com.jdpadron98carlosmc98.cheapfashionapp.Profile;

import android.util.Log;

import com.jdpadron98carlosmc98.cheapfashionapp.data.RepositoryContract;
import com.jdpadron98carlosmc98.cheapfashionapp.data.UserData;

import java.lang.ref.WeakReference;

public class ProfilePresenter implements ProfileContract.Presenter {

    public static String TAG = ProfilePresenter.class.getSimpleName();

    private WeakReference<ProfileContract.View> view;
    private ProfileState state;
    private ProfileContract.Model model;
    private ProfileContract.Router router;

    public ProfilePresenter(ProfileState state) {
        this.state = state;
    }

    @Override
    public void onStart() {
        // initialize the state if is necessary
        if (state == null) {
            state = new ProfileState();
        }

    }

    @Override
    public void onRestart() {

        if (state == null) {
            state = new ProfileState();
        }


    }

    @Override
    public void changeUserData(String name, String phone) {
        model.changeUserData(name, phone, new RepositoryContract.ChangeUserDataCallback() {
            @Override
            public void onChangeUserData(boolean error) {
                if (!error) {
                    view.get().showToast("Successfully updated!");
                } else {
                    view.get().showToast("Fields must not be empty");
                }
            }
        });
    }

    @Override
    public void getUserProfileData() {
        final UserData userData = new UserData("", "", "");
        model.getUserProfileData(userData, new RepositoryContract.OnGetUserProfileDataCallback() {
            @Override
            public void onGetProfileData(boolean error) {
                if (!error) {
                    state.email = userData.getEmail();
                    state.name = userData.getName();
                    state.phone = userData.getPhoneNumber();
                    Log.e(TAG, "state.phone" + userData.getEmail());
                    view.get().updateView(state);
                } else {
                    view.get().showToast("Error retrieving data");
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        view.get().goToHome();
    }

    @Override
    public void injectView(WeakReference<ProfileContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(ProfileContract.Model model) {
        this.model = model;
    }

    @Override
    public void injectRouter(ProfileContract.Router router) {
        this.router = router;
    }

    @Override
    public void goToFavorites() {
        view.get().goToFavorites();
    }

    @Override
    public void goToMyProducts() {
        view.get().goToMyProducts();
    }

    @Override
    public void goToHome() {
        view.get().goToHome();
    }

    @Override
    public void callLogout() {
        model.logout(new RepositoryContract.OnLogoutCallback() {
            @Override
            public void onLogout(boolean error) {
                if (!error) {
                    view.get().showToast("Logged out successfully!");
                    view.get().goToLogin();
                } else {

                }
            }
        });
    }

    @Override
    public void goToChangePass() {
        view.get().goChangePass();
    }
}
