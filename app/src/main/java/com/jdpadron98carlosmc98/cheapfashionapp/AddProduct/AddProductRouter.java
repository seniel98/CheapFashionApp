package com.jdpadron98carlosmc98.cheapfashionapp.AddProduct;

import android.content.Intent;
import android.content.Context;

import com.jdpadron98carlosmc98.cheapfashionapp.app.AppMediator;

public class AddProductRouter implements AddProductContract.Router {

    public static String TAG = AddProductRouter.class.getSimpleName();

    private AppMediator mediator;

    public AddProductRouter(AppMediator mediator) {
        this.mediator = mediator;
    }



    @Override
    public void passStateToNextScreen(AddProductState state) {
        mediator.setNextAddProductScreenState(state);
    }

    @Override
    public void passStateToPreviousScreen(AddProductState state) {
        mediator.setPreviousAddProductScreenState(state);
    }

    @Override
    public void saveState(AddProductState state) {
        mediator.setAddProductState(state);
    }

    @Override
    public AddProductState getAddProductState() {
        return mediator.getAddProductState();
    }

    @Override
    public AddProductState getStateFromPreviousScreen() {
        return mediator.getPreviousAddProductScreenState();
    }

    @Override
    public AddProductState getStateFromNextScreen() {
        return mediator.getNextAddProductScreenState();
    }
}
