package com.jdpadron98carlosmc98.cheapfashionapp.MyProducts;

import androidx.fragment.app.FragmentActivity;

import com.jdpadron98carlosmc98.cheapfashionapp.app.AppMediator;
import com.jdpadron98carlosmc98.cheapfashionapp.app.Repository;
import com.jdpadron98carlosmc98.cheapfashionapp.app.RepositoryContract;

import java.lang.ref.WeakReference;

public class MyProductsScreen {

    public static void configure(MyProductsContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);


        AppMediator mediator = AppMediator.getInstance();
        MyProductsState state = mediator.getMyProductsState();
        RepositoryContract repository = Repository.getInstance(context.get());

        MyProductsContract.Router router = new MyProductsRouter(mediator);
        MyProductsContract.Presenter presenter = new MyProductsPresenter(state);
        MyProductsContract.Model model = new MyProductsModel(repository);
        presenter.injectModel(model);
        presenter.injectRouter(router);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
