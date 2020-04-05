package com.jdpadron98carlosmc98.cheapfashionapp.AddProduct;

import java.lang.ref.WeakReference;

import androidx.fragment.app.FragmentActivity;

import com.jdpadron98carlosmc98.cheapfashionapp.R;
import com.jdpadron98carlosmc98.cheapfashionapp.app.AppMediator;

public class AddProductScreen {

    public static void configure(AddProductContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        String data = context.get().getString(R.string.app_name);

        AppMediator mediator = (AppMediator) context.get().getApplication();
        AddProductState state = mediator.getAddProductState();

        AddProductContract.Router router = new AddProductRouter(mediator);
        AddProductContract.Presenter presenter = new AddProductPresenter(state);
        AddProductContract.Model model = new AddProductModel(data);
        presenter.injectModel(model);
        presenter.injectRouter(router);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
