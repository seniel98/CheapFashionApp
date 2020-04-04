package com.jdpadron98carlosmc98.cheapfashionapp.Home;

import java.lang.ref.WeakReference;

import androidx.fragment.app.FragmentActivity;

import com.jdpadron98carlosmc98.cheapfashionapp.R;

public class HomeScreen {

    public static void configure(HomeContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        String data = context.get().getString(R.string.app_name);

        AppMediator mediator = (AppMediator) context.get().getApplication();
        HomeState state = mediator.getHomeState();

        HomeContract.Router router = new HomeRouter(mediator);
        HomeContract.Presenter presenter = new HomePresenter(state);
        HomeContract.Model model = new HomeModel(data);
        presenter.injectModel(model);
        presenter.injectRouter(router);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
