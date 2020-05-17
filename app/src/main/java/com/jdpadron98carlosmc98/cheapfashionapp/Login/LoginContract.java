package com.jdpadron98carlosmc98.cheapfashionapp.Login;

import com.jdpadron98carlosmc98.cheapfashionapp.data.ProductItem;
import com.jdpadron98carlosmc98.cheapfashionapp.data.RepositoryContract;

import java.lang.ref.WeakReference;
import java.util.List;

public interface LoginContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void setErrorLayoutInputs(int value);

        void cleanErrorInputs();

        void clearInputFocus();

        void goToSignUp();

        void goToForgotPassword();

        void goToHome();

        void navigateToNextScreen();

        void displayData(LoginViewModel viewModel);

        void showToast(String msg);
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void injectRouter(Router router);

        void goToSignUpRouter();

        void goToForgotPasswordRouter();

        void goToHomeRouter();

        void checkLogin(String emailStr, String passStr);

        void onResume();

        void signIn(String emailStr, String passStr);
    }

    interface Model {
        void signIn(String emailStr, String passStr, RepositoryContract.OnSignInCallback onSignInCallback);

        void getDataFromRepository(RepositoryContract.OnGetJSONCallback onGetJSONCallback);

        void insertListInDb(RepositoryContract.onInsertListInDBCallback onInsertListInDBCallback, List<ProductItem> productItems);

    }

    interface Router {

        void passDataToNextScreen(LoginState state);

        LoginState getDataFromPreviousScreen();


    }
}
