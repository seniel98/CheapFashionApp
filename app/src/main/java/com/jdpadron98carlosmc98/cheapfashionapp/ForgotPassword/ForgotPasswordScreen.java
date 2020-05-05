package com.jdpadron98carlosmc98.cheapfashionapp.ForgotPassword;

import androidx.fragment.app.FragmentActivity;

import com.jdpadron98carlosmc98.cheapfashionapp.app.AppMediator;
import com.jdpadron98carlosmc98.cheapfashionapp.data.RepositoryContract;
import com.jdpadron98carlosmc98.cheapfashionapp.data.Repository;

import java.lang.ref.WeakReference;

public class ForgotPasswordScreen {

    public static void configure(ForgotPasswordContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        AppMediator mediator = AppMediator.getInstance();
        ForgotPasswordState state = mediator.getForgotPasswordState();
        //Here we create a repository contract object
        RepositoryContract repository = Repository.getInstance(context.get());

        ForgotPasswordContract.Router router = new ForgotPasswordRouter(mediator);
        ForgotPasswordContract.Presenter presenter = new ForgotPasswordPresenter(state);
        ForgotPasswordContract.Model model = new ForgotPasswordModel(repository);

        presenter.injectModel(model);
        presenter.injectRouter(router);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
