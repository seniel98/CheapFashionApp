package com.jdpadron98carlosmc98.cheapfashionapp.SignUp;

import java.lang.ref.WeakReference;

public interface SignUpContract {

    interface View {
        void injectPresenter(Presenter presenter);

        //void displayData(SignUpViewModel viewModel);
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void injectRouter(Router router);

        //void fetchData();
    }

    interface Model {
        //String fetchData();
    }

    interface Router {
        void navigateToNextScreen();

        void passDataToNextScreen(SignUpState state);

        SignUpState getDataFromPreviousScreen();
    }
}
