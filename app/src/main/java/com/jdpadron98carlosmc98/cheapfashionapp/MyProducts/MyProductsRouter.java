package com.jdpadron98carlosmc98.cheapfashionapp.MyProducts;

import android.util.Log;
import android.content.Intent;
import android.content.Context;

import com.jdpadron98carlosmc98.cheapfashionapp.app.AppMediator;

public class MyProductsRouter implements MyProductsContract.Router {

    public static String TAG = MyProductsRouter.class.getSimpleName();

    private AppMediator mediator;

    public MyProductsRouter(AppMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void navigateToNextScreen() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, MyProductsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void passStateToNextScreen(MyProductsState state) {
        mediator.setNextMyProductsScreenState(state);
    }

    @Override
    public void passStateToPreviousScreen(MyProductsState state) {
        mediator.setPreviousMyProductsScreenState(state);
    }

    @Override
    public MyProductsState getStateFromPreviousScreen() {
        return mediator.getPreviousMyProductsScreenState();
    }

    @Override
    public MyProductsState getStateFromNextScreen() {
        return mediator.getNextMyProductsScreenState();
    }
}
