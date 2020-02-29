package com.jdpadron98carlosmc98.cheapfashionapp.ForgotPassword;

import android.util.Log;

import java.lang.ref.WeakReference;

public class ForgotPasswordPresenter implements ForgotPasswordContract.Presenter {

    public static String TAG = ForgotPasswordPresenter.class.getSimpleName();

    private WeakReference<ForgotPasswordContract.View> view;
    private ForgotPasswordViewModel viewModel;
    private ForgotPasswordContract.Model model;
    private ForgotPasswordContract.Router router;

    public ForgotPasswordPresenter(ForgotPasswordState state) {
        viewModel = state;
    }

   /* @Override
    public void fetchData() {
        // Log.e(TAG, "fetchData()");

        // use passed state if is necessary
        ForgotPasswordState state = router.getDataFromPreviousScreen();
        if (state != null) {

            // update view and model state
            viewModel.data = state.data;

            // update the view
            view.get().displayData(viewModel);

            return;
        }

        // call the model  
        String data = model.fetchData();

        // set view state
        viewModel.data = data;

        // update the view
        view.get().displayData(viewModel);

    }
*/
    @Override
    public void injectView(WeakReference<ForgotPasswordContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(ForgotPasswordContract.Model model) {
        this.model = model;
    }

    @Override
    public void injectRouter(ForgotPasswordContract.Router router) {
        this.router = router;
    }
}
