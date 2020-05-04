package com.jdpadron98carlosmc98.cheapfashionapp.ChangePassword;

import android.widget.EditText;

import com.jdpadron98carlosmc98.cheapfashionapp.app.RepositoryContract;

import java.lang.ref.WeakReference;

public interface ChangePasswordContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void onDataUpdated(ChangePasswordViewModel viewModel);

        void navigateToNextScreen();

        void displayResult(ChangePasswordViewModel viewModel);

        void onBackPressed();
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

        void onSaveClicked(String currentPasswordText, String newPasswordText);
    }

    interface Model {
        String getStoredData();

        void onDataFromNextScreen(String data);

        void onRestartScreen(String data);

        void onDataFromPreviousScreen(String data);

        void onChangePassword(String currentPasswordText, String newPasswordText, RepositoryContract.onChangePasswordCallback onChangePasswordCallback);
    }

    interface Router {

        void passStateToNextScreen(ChangePasswordState state);

        ChangePasswordState getStateFromPreviousScreen();

        ChangePasswordState getStateFromNextScreen();

        void passStateToPreviousScreen(ChangePasswordState state);
    }
}
