package com.jdpadron98carlosmc98.cheapfashionapp.Home;

import java.lang.ref.WeakReference;

import androidx.fragment.app.FragmentActivity;

import com.jdpadron98carlosmc98.cheapfashionapp.app.AppMediator;
import com.jdpadron98carlosmc98.cheapfashionapp.data.Repository;
import com.jdpadron98carlosmc98.cheapfashionapp.data.RepositoryContract;

public class HomeScreen {

    public static void configure(HomeContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);


        AppMediator mediator = AppMediator.getInstance();
        HomeState state = mediator.getHomeState();
        RepositoryContract repository = Repository.getInstance(context.get());

        HomeContract.Router router = new HomeRouter(mediator);
        HomeContract.Presenter presenter = new HomePresenter(state);
        HomeContract.Model model = new HomeModel(repository);
        presenter.injectModel(model);
        presenter.injectRouter(router);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
