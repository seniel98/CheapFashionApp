package com.jdpadron98carlosmc98.cheapfashionapp.SignUp;

import com.jdpadron98carlosmc98.cheapfashionapp.data.RepositoryContract;
import com.jdpadron98carlosmc98.cheapfashionapp.data.UserData;

import java.lang.ref.WeakReference;

public interface SignUpContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void onBackPressed();

        void showToast(SignUpViewModel msg);

        //void displayData(SignUpViewModel viewModel);
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void injectRouter(Router router);

        void signUpClicked(UserData userData, String pass);

        //void fetchData();
    }

    interface Model {
        void signUp(UserData userData, String pass, RepositoryContract.OnSignUpCallback OnSignUpCallback);
        //String fetchData();
    }

    interface Router {

        void passDataToNextScreen(SignUpState state);

        SignUpState getDataFromPreviousScreen();
    }
}
