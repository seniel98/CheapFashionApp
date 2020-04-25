package com.jdpadron98carlosmc98.cheapfashionapp.SplashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.jdpadron98carlosmc98.cheapfashionapp.Login.LoginActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.R;
import com.jdpadron98carlosmc98.cheapfashionapp.app.AppMediator;

public class SplashScreenActivity
        extends AppCompatActivity implements SplashScreenContract.View {

    public static String TAG = SplashScreenActivity.class.getSimpleName();

    private SplashScreenContract.Presenter presenter;

    private static int SPLASH_TIMEOUT = 1500; //1'5 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Deshalibitada la animacion para ejecutar los tests
        //startAnimations();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.goToLogin();
            }
        }, SPLASH_TIMEOUT);


        // do the setup
        if (savedInstanceState == null) {
            AppMediator.resetInstance();
        }

        SplashScreenScreen.configure(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void goToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    private void startAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout l = findViewById(R.id.splashLayout);
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView appLogo = findViewById(R.id.appLogo);
        ImageView welcomeMessage = findViewById(R.id.welcomeMessage);
        appLogo.clearAnimation();
        welcomeMessage.clearAnimation();
        appLogo.startAnimation(anim);
        welcomeMessage.startAnimation(anim);
    }

    @Override
    public void injectPresenter(SplashScreenContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
