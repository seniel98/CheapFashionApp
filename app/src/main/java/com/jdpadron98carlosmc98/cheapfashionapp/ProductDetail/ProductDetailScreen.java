package com.jdpadron98carlosmc98.cheapfashionapp.ProductDetail;

import java.lang.ref.WeakReference;

import androidx.fragment.app.FragmentActivity;

import com.jdpadron98carlosmc98.cheapfashionapp.R;
import com.jdpadron98carlosmc98.cheapfashionapp.app.AppMediator;

public class ProductDetailScreen {

    public static void configure(ProductDetailContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        String data = context.get().getString(R.string.app_name);

        AppMediator mediator = (AppMediator) context.get().getApplication();
        ProductDetailState state = mediator.getProductDetailState();

        ProductDetailContract.Router router = new ProductDetailRouter(mediator);
        ProductDetailContract.Presenter presenter = new ProductDetailPresenter(state);
        ProductDetailContract.Model model = new ProductDetailModel(data);
        presenter.injectModel(model);
        presenter.injectRouter(router);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}