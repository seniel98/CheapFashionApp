package com.jdpadron98carlosmc98.cheapfashionapp.MyProducts;

import android.content.Context;
import android.content.Intent;

import com.jdpadron98carlosmc98.cheapfashionapp.AddProduct.AddProductActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.Favorite.FavoriteActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.Home.HomeActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.Login.LoginActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.Profile.ProfileActivity;
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
    public void goToFavorites() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, FavoriteActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void goToHome() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void goToProfile() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, ProfileActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void goToLogin() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void goAddProduct() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, AddProductActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
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
