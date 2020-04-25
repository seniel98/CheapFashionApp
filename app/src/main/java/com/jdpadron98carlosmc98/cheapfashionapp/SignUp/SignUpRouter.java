package com.jdpadron98carlosmc98.cheapfashionapp.SignUp;

import com.jdpadron98carlosmc98.cheapfashionapp.app.AppMediator;

public class SignUpRouter implements SignUpContract.Router {

    public static String TAG = SignUpRouter.class.getSimpleName();

    private AppMediator mediator;

    public SignUpRouter(AppMediator mediator) {
        this.mediator = mediator;
    }


    @Override
    public void passDataToNextScreen(SignUpState state) {
        mediator.setSignUpState(state);
    }

    @Override
    public SignUpState getDataFromPreviousScreen() {
        SignUpState state = mediator.getSignUpState();
        return state;
    }
}
