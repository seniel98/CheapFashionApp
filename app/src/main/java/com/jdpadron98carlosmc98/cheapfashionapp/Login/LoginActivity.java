package com.jdpadron98carlosmc98.cheapfashionapp.Login;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jdpadron98carlosmc98.cheapfashionapp.R;

public class LoginActivity
        extends AppCompatActivity implements LoginContract.View {

    public static String TAG = LoginActivity.class.getSimpleName();

    private LoginContract.Presenter presenter;
    private TextView signUpText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initLayout();
        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.goToSignUpRouter();
            }
        });
        // do the setup
        LoginScreen.configure(this);
    }


    private void initLayout(){
        signUpText = findViewById(R.id.signUpText);
    }
    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    @Override
    public void injectPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
