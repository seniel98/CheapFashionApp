package com.jdpadron98carlosmc98.cheapfashionapp.Home;

import android.content.Context;
import android.content.Intent;

import com.jdpadron98carlosmc98.cheapfashionapp.Favorite.FavoriteActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.Login.LoginActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.MyProducts.MyProductsActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.Profile.ProfileActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.app.AppMediator;

public class HomeRouter implements HomeContract.Router {

    public static String TAG = HomeRouter.class.getSimpleName();

    private AppMediator mediator;

    public HomeRouter(AppMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void navigateToNextScreen() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void passStateToNextScreen(HomeState state) {
        mediator.setNextHomeScreenState(state);
    }

    @Override
    public void passStateToPreviousScreen(HomeState state) {
        mediator.setPreviousHomeScreenState(state);
    }

    @Override
    public void goToFavorites() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, FavoriteActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void goToMyProducts() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, MyProductsActivity.class);
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
    public HomeState getStateFromPreviousScreen() {
        return mediator.getPreviousHomeScreenState();
    }

    @Override
    public HomeState getStateFromNextScreen() {
        return mediator.getNextHomeScreenState();
    }


}
