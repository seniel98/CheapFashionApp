package com.jdpadron98carlosmc98.cheapfashionapp.ForgotPassword;

import java.lang.ref.WeakReference;

public interface ForgotPasswordContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void navigateToNextScreen();

       // void displayData(ForgotPasswordViewModel viewModel);
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void injectRouter(Router router);

        //void fetchData();
    }

    interface Model {
       // String fetchData();
    }

    interface Router {

        void passDataToNextScreen(ForgotPasswordState state);

        ForgotPasswordState getDataFromPreviousScreen();
    }
}
