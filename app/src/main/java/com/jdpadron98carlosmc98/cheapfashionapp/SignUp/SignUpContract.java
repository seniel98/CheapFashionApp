package com.jdpadron98carlosmc98.cheapfashionapp.SignUp;

import com.jdpadron98carlosmc98.cheapfashionapp.app.RepositoryContract;
import com.jdpadron98carlosmc98.cheapfashionapp.app.UserData;

import java.lang.ref.WeakReference;

public interface SignUpContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void onBackPressed();

        void showToast(String msg);

        //void displayData(SignUpViewModel viewModel);
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void injectRouter(Router router);

        void signUpClicked(String name, String email, String phone, String pass);

        //void fetchData();
    }

    interface Model {
        void signUp(UserData userData, String pass, RepositoryContract.RegisterCallback registerCallback);
        //String fetchData();
    }

    interface Router {

        void passDataToNextScreen(SignUpState state);

        SignUpState getDataFromPreviousScreen();
    }
}
