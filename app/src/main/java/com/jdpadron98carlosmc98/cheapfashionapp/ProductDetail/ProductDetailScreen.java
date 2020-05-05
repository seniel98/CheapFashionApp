package com.jdpadron98carlosmc98.cheapfashionapp.ProductDetail;

import java.lang.ref.WeakReference;

import androidx.fragment.app.FragmentActivity;

import com.jdpadron98carlosmc98.cheapfashionapp.R;
import com.jdpadron98carlosmc98.cheapfashionapp.app.AppMediator;
import com.jdpadron98carlosmc98.cheapfashionapp.app.Repository;
import com.jdpadron98carlosmc98.cheapfashionapp.app.RepositoryContract;

public class ProductDetailScreen {

    public static void configure(ProductDetailContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        String data = context.get().getString(R.string.app_name);

        AppMediator mediator = AppMediator.getInstance();
        ProductDetailState state = mediator.getProductDetailState();
        RepositoryContract repository = Repository.getInstance(context.get());


        ProductDetailContract.Router router = new ProductDetailRouter(mediator);
        ProductDetailContract.Presenter presenter = new ProductDetailPresenter(state);
        ProductDetailContract.Model model = new ProductDetailModel(repository);
        presenter.injectModel(model);
        presenter.injectRouter(router);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
