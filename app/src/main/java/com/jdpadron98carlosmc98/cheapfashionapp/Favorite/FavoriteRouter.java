package com.jdpadron98carlosmc98.cheapfashionapp.Favorite;

import android.content.Context;
import android.content.Intent;

import com.jdpadron98carlosmc98.cheapfashionapp.Home.HomeActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.Login.LoginActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.MyProducts.MyProductsActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.ProductDetail.ProductDetailActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.Profile.ProfileActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.app.AppMediator;

public class FavoriteRouter implements FavoriteContract.Router {

    public static String TAG = FavoriteRouter.class.getSimpleName();

    private AppMediator mediator;

    public FavoriteRouter(AppMediator mediator) {
        this.mediator = mediator;
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
    public FavoriteState getStateFromPreviousScreen() {
        return mediator.getPreviousFavoriteScreenState();
    }

    @Override
    public FavoriteState getStateFromNextScreen() {
        return mediator.getNextFavoriteScreenState();
    }
}
