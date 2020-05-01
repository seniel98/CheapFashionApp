package com.jdpadron98carlosmc98.cheapfashionapp.SignUp;

import com.jdpadron98carlosmc98.cheapfashionapp.app.RepositoryContract;
import com.jdpadron98carlosmc98.cheapfashionapp.app.UserData;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

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
    public void signUpClicked(String name, String email, String phone, String pass) {
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || pass.isEmpty()) {

            view.get().showToast("Fields must not be empty");

        } else {
            callModelToSignUp(name, email, phone, pass);
        }
    }

    private void callModelToSignUp(String name, String email, String phone, String pass) {
        UserData userData = new UserData(name, email, phone, new ArrayList<String>(), new ArrayList<String>());
        model.signUp(userData, pass, new RepositoryContract.RegisterCallback() {
            @Override
            public void createUserError(boolean error, String msg) {
                if (!error) {
                    view.get().showToast("Registered successfully");
                    view.get().onBackPressed();
                } else {
                    view.get().showToast(msg);
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
