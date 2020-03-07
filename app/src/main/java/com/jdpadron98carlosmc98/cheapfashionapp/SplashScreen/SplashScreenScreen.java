package com.jdpadron98carlosmc98.cheapfashionapp.SplashScreen;

import androidx.fragment.app.FragmentActivity;

import com.jdpadron98carlosmc98.cheapfashionapp.app.AppMediator;
import com.jdpadron98carlosmc98.cheapfashionapp.app.RepositoryContract;
import com.jdpadron98carlosmc98.cheapfashionapp.app.Repository;

import java.lang.ref.WeakReference;

public class SplashScreenScreen {

    public static void configure(SplashScreenContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        AppMediator mediator = (AppMediator) context.get().getApplication();
        SplashScreenState state = mediator.getSplashScreenState();
        //Here we create a repository contract object
        RepositoryContract repository = Repository.getInstance(context.get());

        SplashScreenContract.Router router = new SplashScreenRouter(mediator);
        SplashScreenContract.Presenter presenter = new SplashScreenPresenter(state);
        SplashScreenContract.Model model = new SplashScreenModel(repository);
        presenter.injectModel(model);
        presenter.injectRouter(router);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
