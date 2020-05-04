package com.jdpadron98carlosmc98.cheapfashionapp.ForgotPassword;

import com.jdpadron98carlosmc98.cheapfashionapp.app.RepositoryContract;

import java.lang.ref.WeakReference;

public interface ForgotPasswordContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void navigateToNextScreen();

        void displayResult();

        // void displayData(ForgotPasswordViewModel viewModel);
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void injectRouter(Router router);

        void onSendClicked(String email);

        //void fetchData();
    }

    interface Model {
        void onForgetPassword(String email, RepositoryContract.onForgetPasswordCallback onForgetPasswordCallback);
        // String fetchData();
    }

    interface Router {

        void passDataToNextScreen(ForgotPasswordState state);

        ForgotPasswordState getDataFromPreviousScreen();
    }
}
