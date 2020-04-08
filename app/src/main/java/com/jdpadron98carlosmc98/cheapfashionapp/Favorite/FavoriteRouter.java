package com.jdpadron98carlosmc98.cheapfashionapp.Favorite;

import android.content.Context;
import android.content.Intent;

import com.jdpadron98carlosmc98.cheapfashionapp.Home.HomeActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.Login.LoginActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.MyProducts.MyProductsActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.Profile.ProfileActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.app.AppMediator;

public class FavoriteRouter implements FavoriteContract.Router {

    public static String TAG = FavoriteRouter.class.getSimpleName();

    private AppMediator mediator;

    public FavoriteRouter(AppMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void navigateToNextScreen() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, FavoriteActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void passStateToNextScreen(FavoriteState state) {
        mediator.setNextFavoriteScreenState(state);
    }

    @Override
    public void passStateToPreviousScreen(FavoriteState state) {
        mediator.setPreviousFavoriteScreenState(state);
    }

    @Override
    public void goToHome() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void goToMyProducts() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, MyProductsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void goToProfile() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, ProfileActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
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
    public FavoriteState getStateFromPreviousScreen() {
        return mediator.getPreviousFavoriteScreenState();
    }

    @Override
    public FavoriteState getStateFromNextScreen() {
        return mediator.getNextFavoriteScreenState();
    }
}
