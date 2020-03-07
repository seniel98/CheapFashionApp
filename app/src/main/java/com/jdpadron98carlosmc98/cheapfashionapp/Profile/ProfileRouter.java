package com.jdpadron98carlosmc98.cheapfashionapp.Profile;

import android.content.Context;
import android.content.Intent;

import com.jdpadron98carlosmc98.cheapfashionapp.app.AppMediator;

public class ProfileRouter implements ProfileContract.Router {

    public static String TAG = ProfileRouter.class.getSimpleName();

    private AppMediator mediator;

    public ProfileRouter(AppMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void navigateToNextScreen() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, ProfileActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void passDataToNextScreen(ProfileState state) {
        mediator.setProfileState(state);
    }

    @Override
    public ProfileState getDataFromPreviousScreen() {
        ProfileState state = mediator.getProfileState();
        return state;
    }
}
