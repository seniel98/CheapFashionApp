package com.jdpadron98carlosmc98.cheapfashionapp.Profile;

import androidx.fragment.app.FragmentActivity;

import com.jdpadron98carlosmc98.cheapfashionapp.app.AppMediator;
import com.jdpadron98carlosmc98.cheapfashionapp.app.Repository;
import com.jdpadron98carlosmc98.cheapfashionapp.app.RepositoryContract;

import java.lang.ref.WeakReference;

public class ProfileScreen {

    public static void configure(ProfileContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        AppMediator mediator = (AppMediator) context.get().getApplication();
        ProfileState state = mediator.getProfileState();
        //Here we create a repository contract object
        RepositoryContract repository = Repository.getInstance(context.get());


        ProfileContract.Router router = new ProfileRouter(mediator);
        ProfileContract.Presenter presenter = new ProfilePresenter(state);
        ProfileContract.Model model = new ProfileModel(repository);
        presenter.injectModel(model);
        presenter.injectRouter(router);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
