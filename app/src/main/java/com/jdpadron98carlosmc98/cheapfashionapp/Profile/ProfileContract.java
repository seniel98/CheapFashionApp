package com.jdpadron98carlosmc98.cheapfashionapp.Profile;

import java.lang.ref.WeakReference;

public interface ProfileContract {

    interface View {
        void injectPresenter(Presenter presenter);

        //void displayData(ProfileViewModel viewModel);
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

        void passDataToNextScreen(ProfileState state);

        ProfileState getDataFromPreviousScreen();
    }
}
