package com.jdpadron98carlosmc98.cheapfashionapp.SignUp;

import com.google.firebase.firestore.auth.User;
import com.jdpadron98carlosmc98.cheapfashionapp.data.RepositoryContract;
import com.jdpadron98carlosmc98.cheapfashionapp.data.UserData;

import java.lang.ref.WeakReference;

public class SignUpPresenter implements SignUpContract.Presenter {

    public static String TAG = SignUpPresenter.class.getSimpleName();

    private WeakReference<SignUpContract.View> view;
    private SignUpViewModel viewModel;
    private SignUpContract.Model model;
    private SignUpContract.Router router;

    public SignUpPresenter(SignUpState state) {
        viewModel = state;
    }

  /*  @Override
    public void fetchData() {
        // Log.e(TAG, "fetchData()");

        // use passed state if is necessary
        SignUpState state = router.getDataFromPreviousScreen();
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

    }*/


    @Override
    public void signUpClicked(UserData userData, String pass) {
        if (userData.getName().isEmpty() || userData.getEmail().isEmpty() || userData.getPhoneNumber().isEmpty() || pass.isEmpty()) {
            viewModel.message = "Fields must not be empty";
            view.get().showToast(viewModel);

        } else {
            callModelToSignUp(userData, pass);
        }
    }

    private void callModelToSignUp(UserData userData, String pass) {
        model.signUp(userData, pass, new RepositoryContract.OnSignUpCallback() {
            @Override
            public void onSignUp(boolean error, String msg) {
                if (!error) {
                    viewModel.message = msg;
                    view.get().showToast(viewModel);
                    view.get().onBackPressed();
                } else {
                    viewModel.message = msg;
                    view.get().showToast(viewModel);
                }
            }
        });
    }

    @Override
    public void injectView(WeakReference<SignUpContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(SignUpContract.Model model) {
        this.model = model;
    }

    @Override
    public void injectRouter(SignUpContract.Router router) {
        this.router = router;
    }
}
