package com.jdpadron98carlosmc98.cheapfashionapp.ChangePassword;

import com.jdpadron98carlosmc98.cheapfashionapp.data.RepositoryContract;

import java.lang.ref.WeakReference;

public class ChangePasswordPresenter implements ChangePasswordContract.Presenter {

    public static String TAG = ChangePasswordPresenter.class.getSimpleName();

    private WeakReference<ChangePasswordContract.View> view;
    private ChangePasswordState state;
    private ChangePasswordContract.Model model;
    private ChangePasswordContract.Router router;

    public ChangePasswordPresenter(ChangePasswordState state) {
        this.state = state;
    }

    @Override
    public void onStart() {
        // Log.e(TAG, "onStart()");

        // initialize the state if is necessary
        if (state == null) {
            state = new ChangePasswordState();
        }

        // use passed state if is necessary
        ChangePasswordState savedState = router.getStateFromPreviousScreen();
        if (savedState != null) {

            // update the model if is necessary
            //model.onDataFromPreviousScreen(savedState.data);
        }
    }

    @Override
    public void onRestart() {
        // Log.e(TAG, "onRestart()");

        // update the model if is necessary
        //model.onRestartScreen(state.data);
    }

    @Override
    public void onResume() {
        // Log.e(TAG, "onResume()");

        // use passed state if is necessary
        ChangePasswordState savedState = router.getStateFromNextScreen();
        if (savedState != null) {

            // update the model if is necessary
            //model.onDataFromNextScreen(savedState.data);
        }

        // call the model and update the state
        //state.data = model.getStoredData();

        // update the view
        view.get().onDataUpdated(state);

    }

    @Override
    public void onBackPressed() {
        // Log.e(TAG, "onBackPressed()");
    }

    @Override
    public void onPause() {
        // Log.e(TAG, "onPause()");
    }

    @Override
    public void onDestroy() {
        // Log.e(TAG, "onDestroy()");
    }

    /**
     * Metodo que crea un mensaje en caso de que se pueda modificar la contraseña.
     *
     * @param currentPasswordText
     * @param newPasswordText
     */
    @Override
    public void onSaveClicked(String currentPasswordText, String newPasswordText) {
        model.onChangePassword(currentPasswordText, newPasswordText, new RepositoryContract.onChangePasswordCallback() {

            @Override
            public void onChangePassword(boolean error, String message) {
                state.message = message;
                view.get().displayResult(state);
                if(!error){
                    view.get().onBackPressed();
                }
            }
        });
    }

    @Override
    public void injectView(WeakReference<ChangePasswordContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(ChangePasswordContract.Model model) {
        this.model = model;
    }

    @Override
    public void injectRouter(ChangePasswordContract.Router router) {
        this.router = router;
    }
}
