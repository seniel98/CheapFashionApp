package com.jdpadron98carlosmc98.cheapfashionapp.Favorite;

import java.lang.ref.WeakReference;

import androidx.fragment.app.FragmentActivity;

import com.jdpadron98carlosmc98.cheapfashionapp.R;
import com.jdpadron98carlosmc98.cheapfashionapp.app.AppMediator;

public class FavoriteScreen {

    public static void configure(FavoriteContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        String data = context.get().getString(R.string.app_name);

        AppMediator mediator = (AppMediator) context.get().getApplication();
        FavoriteState state = mediator.getFavoriteState();

        FavoriteContract.Router router = new FavoriteRouter(mediator);
        FavoriteContract.Presenter presenter = new FavoritePresenter(state);
        FavoriteContract.Model model = new FavoriteModel(data);
        presenter.injectModel(model);
        presenter.injectRouter(router);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
