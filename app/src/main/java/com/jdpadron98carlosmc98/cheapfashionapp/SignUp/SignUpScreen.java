package com.jdpadron98carlosmc98.cheapfashionapp.SignUp;

import androidx.fragment.app.FragmentActivity;

import com.jdpadron98carlosmc98.cheapfashionapp.app.AppMediator;
import com.jdpadron98carlosmc98.cheapfashionapp.app.Contract;
import com.jdpadron98carlosmc98.cheapfashionapp.app.Repository;

import java.lang.ref.WeakReference;

public class SignUpScreen {

    public static void configure(SignUpContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        AppMediator mediator = (AppMediator) context.get().getApplication();
        SignUpState state = mediator.getSignUpState();
        //Here we create a repository contract object
        Contract repository = Repository.getInstance(context.get());


        SignUpContract.Router router = new SignUpRouter(mediator);
        SignUpContract.Presenter presenter = new SignUpPresenter(state);
        SignUpContract.Model model = new SignUpModel(repository);
        presenter.injectModel(model);
        presenter.injectRouter(router);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
