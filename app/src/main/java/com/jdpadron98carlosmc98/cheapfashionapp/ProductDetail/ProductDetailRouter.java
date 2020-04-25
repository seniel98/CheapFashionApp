package com.jdpadron98carlosmc98.cheapfashionapp.ProductDetail;

import com.jdpadron98carlosmc98.cheapfashionapp.app.AppMediator;

public class ProductDetailRouter implements ProductDetailContract.Router {

    public static String TAG = ProductDetailRouter.class.getSimpleName();

    private AppMediator mediator;

    public ProductDetailRouter(AppMediator mediator) {
        this.mediator = mediator;
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
