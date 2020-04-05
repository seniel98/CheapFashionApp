package com.jdpadron98carlosmc98.cheapfashionapp.ProductDetail;

import android.util.Log;
import android.content.Intent;
import android.content.Context;

import com.jdpadron98carlosmc98.cheapfashionapp.app.AppMediator;

public class ProductDetailRouter implements ProductDetailContract.Router {

    public static String TAG = ProductDetailRouter.class.getSimpleName();

    private AppMediator mediator;

    public ProductDetailRouter(AppMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void navigateToNextScreen() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void passStateToNextScreen(ProductDetailState state) {
        mediator.setNextProductDetailScreenState(state);
    }

    @Override
    public void passStateToPreviousScreen(ProductDetailState state) {
        mediator.setPreviousProductDetailScreenState(state);
    }

    @Override
    public ProductDetailState getStateFromPreviousScreen() {
        return mediator.getPreviousProductDetailScreenState();
    }

    @Override
    public ProductDetailState getStateFromNextScreen() {
        return mediator.getNextProductDetailScreenState();
    }
}
