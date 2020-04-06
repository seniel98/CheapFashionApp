package com.jdpadron98carlosmc98.cheapfashionapp.Favorite;

import android.util.Log;
import android.content.Intent;
import android.content.Context;

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
    public FavoriteState getStateFromPreviousScreen() {
        return mediator.getPreviousFavoriteScreenState();
    }

    @Override
    public FavoriteState getStateFromNextScreen() {
        return mediator.getNextFavoriteScreenState();
    }
}
