package com.jdpadron98carlosmc98.cheapfashionapp.SplashScreen;

import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.jdpadron98carlosmc98.cheapfashionapp.R;

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
                presenter.goToRouterLogin();
            }
        }, SPLASH_TIMEOUT);


        // do the setup
        SplashScreenScreen.configure(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

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
