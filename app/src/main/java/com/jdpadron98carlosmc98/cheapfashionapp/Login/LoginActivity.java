package com.jdpadron98carlosmc98.cheapfashionapp.Login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.jdpadron98carlosmc98.cheapfashionapp.R;

public class LoginActivity
        extends AppCompatActivity implements LoginContract.View {

    public static String TAG = LoginActivity.class.getSimpleName();

    private LoginContract.Presenter presenter;
    private TextView signUpText, fashionLabelText, forgotPass;
    private EditText emailText, passText;
    private TextInputLayout emailTextInputLayout, passTextInputLayout;
    private MaterialButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //---------------------------------------------//
        initLoginLayout();
        setUpLoginLayout();
        onSignUpText();
        // do the setup
        LoginScreen.configure(this);
    }

    /**
     * Inicializamos los distintos elementos de la vista
     */
    private void initLoginLayout() {
        loginButton = findViewById(R.id.loginButton);
        forgotPass = findViewById(R.id.forgotPass);
        fashionLabelText = findViewById(R.id.fashionLabelText);
        signUpText = findViewById(R.id.signUpText);
        passText = findViewById(R.id.passLoginText);
        emailText = findViewById(R.id.emailLoginText);
        passTextInputLayout = findViewById(R.id.passLoginTextInputLayout);
        emailTextInputLayout = findViewById(R.id.emailLoginTextInputLayout);
    }

    /**
     * Fijamos los textos del layout
     */
    private void setUpLoginLayout() {
        loginButton.setText(R.string.loginButtonLabel);
        forgotPass.setText(R.string.forgotPass);
        fashionLabelText.setText(R.string.fashionLoginLabel);
        signUpText.setText(R.string.signUpText);
    }

    /**
     * Metodo para establecer un onClickListener para el texto de signUp
     */

    private void onSignUpText() {
        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.goToSignUpRouter();
            }
        });
    }

    public void onForgotPassText(View view){
        presenter.goToForgotPasswordRouter();
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
