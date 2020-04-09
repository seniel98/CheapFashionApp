package com.jdpadron98carlosmc98.cheapfashionapp.Favorite;

import java.lang.ref.WeakReference;

import androidx.fragment.app.FragmentActivity;

import com.jdpadron98carlosmc98.cheapfashionapp.R;
import com.jdpadron98carlosmc98.cheapfashionapp.app.AppMediator;
import com.jdpadron98carlosmc98.cheapfashionapp.app.Repository;
import com.jdpadron98carlosmc98.cheapfashionapp.app.RepositoryContract;

public class FavoriteScreen {

    public static void configure(FavoriteContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);


        AppMediator mediator = (AppMediator) context.get().getApplication();
        FavoriteState state = mediator.getFavoriteState();
        RepositoryContract repository = Repository.getInstance(context.get());

        FavoriteContract.Router router = new FavoriteRouter(mediator);
        FavoriteContract.Presenter presenter = new FavoritePresenter(state);
        FavoriteContract.Model model = new FavoriteModel(repository);
        presenter.injectModel(model);
        presenter.injectRouter(router);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
