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
    public void navigateToNextScreen() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, AddProductActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
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
    public AddProductState getStateFromPreviousScreen() {
        return mediator.getPreviousAddProductScreenState();
    }

    @Override
    public AddProductState getStateFromNextScreen() {
        return mediator.getNextAddProductScreenState();
    }
}
