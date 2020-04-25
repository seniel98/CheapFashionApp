package com.jdpadron98carlosmc98.cheapfashionapp.Home;

import android.content.Context;
import android.content.Intent;

import com.jdpadron98carlosmc98.cheapfashionapp.AddProduct.AddProductActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.Favorite.FavoriteActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.Login.LoginActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.MyProducts.MyProductsActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.ProductDetail.ProductDetailActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.Profile.ProfileActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.app.AppMediator;

public class HomeRouter implements HomeContract.Router {

    public static String TAG = HomeRouter.class.getSimpleName();

    private AppMediator mediator;

    public HomeRouter(AppMediator mediator) {
        this.mediator = mediator;
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
    public HomeState getStateFromPreviousScreen() {
        return mediator.getPreviousHomeScreenState();
    }

    @Override
    public HomeState getStateFromNextScreen() {
        return mediator.getNextHomeScreenState();
    }


}
