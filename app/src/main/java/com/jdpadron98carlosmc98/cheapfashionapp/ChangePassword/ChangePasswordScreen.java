package com.jdpadron98carlosmc98.cheapfashionapp.ChangePassword;

import java.lang.ref.WeakReference;

import androidx.fragment.app.FragmentActivity;

import com.jdpadron98carlosmc98.cheapfashionapp.R;
import com.jdpadron98carlosmc98.cheapfashionapp.app.AppMediator;

public class ChangePasswordScreen {

    public static void configure(ChangePasswordContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        String data = context.get().getString(R.string.app_name);

        AppMediator mediator = AppMediator.getInstance();
        ChangePasswordState state = mediator.getChangePasswordState();

        ChangePasswordContract.Router router = new ChangePasswordRouter(mediator);
        ChangePasswordContract.Presenter presenter = new ChangePasswordPresenter(state);
        ChangePasswordContract.Model model = new ChangePasswordModel(data);
        presenter.injectModel(model);
        presenter.injectRouter(router);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
