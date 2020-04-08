package com.jdpadron98carlosmc98.cheapfashionapp.Profile;

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

    /*
       @Override
        public void fetchData() {
            // Log.e(TAG, "fetchData()");

            // initialize the state if is necessary
            if (state == null) {
                state = new ProfileState();
            }

            // use passed state if is necessary
            ProfileState savedState = router.getDataFromPreviousScreen();
            if (savedState != null) {

                // update view and model state
                state.data = savedState.data;

                // update the view
                view.get().displayData(state);

                return;
            }

            // call the model
            String data = model.fetchData();

            // set view state
            state.data = data;

            // update the view
            view.get().displayData(state);

        }
    */


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
    public void goToFavoritesRouter() {
        router.goToFavorites();
    }

    @Override
    public void goToMyProductsRouter() {
        router.goToMyProducts();
    }

    @Override
    public void goToHomeRouter() {
        router.goToHome();
    }

    @Override
    public void callLogout() {
        router.goToLogin();
    }
}
