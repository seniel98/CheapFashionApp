package com.jdpadron98carlosmc98.cheapfashionapp.ChangePassword;

import java.lang.ref.WeakReference;

public interface ChangePasswordContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void onDataUpdated(ChangePasswordViewModel viewModel);
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

        void passStateToNextScreen(ChangePasswordState state);

        ChangePasswordState getStateFromPreviousScreen();

        ChangePasswordState getStateFromNextScreen();

        void passStateToPreviousScreen(ChangePasswordState state);
    }
}
