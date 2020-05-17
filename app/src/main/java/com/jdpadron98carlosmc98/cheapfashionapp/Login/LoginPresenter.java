package com.jdpadron98carlosmc98.cheapfashionapp.Login;

import com.jdpadron98carlosmc98.cheapfashionapp.data.ProductItem;
import com.jdpadron98carlosmc98.cheapfashionapp.data.RepositoryContract;

import java.lang.ref.WeakReference;
import java.util.List;

public class LoginPresenter implements LoginContract.Presenter {

    public static String TAG = LoginPresenter.class.getSimpleName();

    private WeakReference<LoginContract.View> view;
    private LoginViewModel viewModel;
    private LoginContract.Model model;
    private LoginContract.Router router;

    public LoginPresenter(LoginState state) {
        viewModel = state;
    }


    @Override
    public void onResume() {

        view.get().cleanErrorInputs();
        view.get().clearInputFocus();
    }

    @Override
    public void signIn(String emailStr, String passStr) {
        model.signIn(emailStr, passStr, new RepositoryContract.OnSignInCallback() {
            @Override
            public void onSignIn(boolean error) {
                if (!error) {
                    downloadDataFromRepository();
                    //goToHomeRouter();
                } else {
                    viewModel.message = "This user does not exist";
                    view.get().displayData(viewModel);

                }
            }
        });
    }

    private void downloadDataFromRepository() {
        model.getDataFromRepository(new RepositoryContract.OnGetJSONCallback() {
            @Override
            public void onGetJSON(boolean error, List<ProductItem> productItems) {
                if (error) {
                    view.get().showToast("NO CONNECTION. DATA MAY BE OBSOLETE");
                    insertListInDb(productItems);
                } else {
                    insertListInDb(productItems);
                }
            }
        });
    }

    private void insertListInDb(List<ProductItem> productItems) {
        model.insertListInDb(new RepositoryContract.onInsertListInDBCallback() {
            @Override
            public void onInsert(boolean error) {
                if (!error) {
                    view.get().goToHome();
                }
            }
        }, productItems);
    }


    @Override
    public void checkLogin(String emailStr, String passStr) {
        if (emailStr.isEmpty() && passStr.isEmpty()) {
            view.get().setErrorLayoutInputs(2);
        } else if (emailStr.isEmpty()) {
            view.get().setErrorLayoutInputs(0);
        } else if (passStr.isEmpty()) {
            view.get().setErrorLayoutInputs(1);
        } else {
            //view.get().enableProgressBar();
            //callModel
            signIn(emailStr, passStr);
        }
    }

    @Override
    public void injectView(WeakReference<LoginContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(LoginContract.Model model) {
        this.model = model;
    }

    @Override
    public void injectRouter(LoginContract.Router router) {
        this.router = router;
    }

    @Override
    public void goToHomeRouter() {
        view.get().goToHome();
    }

    @Override
    public void goToSignUpRouter() {
        view.get().goToSignUp();
    }

    @Override
    public void goToForgotPasswordRouter() {
        view.get().goToForgotPassword();
    }
}
