package com.jdpadron98carlosmc98.cheapfashionapp.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.jdpadron98carlosmc98.cheapfashionapp.ForgotPassword.ForgotPasswordActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.Home.HomeActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.R;
import com.jdpadron98carlosmc98.cheapfashionapp.SignUp.SignUpActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.app.AppMediator;

public class LoginActivity
        extends AppCompatActivity implements LoginContract.View {

    public static String TAG = LoginActivity.class.getSimpleName();

    private LoginContract.Presenter presenter;
    private TextView signUpText, fashionLabelText, forgotPass;
    private TextInputEditText emailText, passText;
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

        checkErrors();

        if (savedInstanceState == null) {
            AppMediator.resetInstance();
        }
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

    public void onForgotPassText(View view) {
        presenter.goToForgotPasswordRouter();
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.onResume();

    }


    public void clearInputFocus() {
        emailTextInputLayout.clearFocus();
        passTextInputLayout.clearFocus();
    }

    public void cleanErrorInputs() {
        cleanEmailError();
        cleanPassError();
    }

    private void cleanEmailError() {
        emailTextInputLayout.setError(null);
        emailTextInputLayout.setErrorEnabled(false);
    }

    private void cleanPassError() {
        passTextInputLayout.setError(null);
        passTextInputLayout.setErrorEnabled(false);
    }

    @Override
    public void goToSignUp() {
        Intent intent = new Intent(this, SignUpActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void goToForgotPassword() {
        Intent intent = new Intent(this, ForgotPasswordActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void navigateToNextScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void displayData(LoginViewModel viewModel) {
        Toast.makeText(getApplicationContext(), viewModel.message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void goToHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    @Override
    public void injectPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }


    public void onLoginClicked(View view) {
        String emailStr = emailText.getText().toString();
        String passStr = passText.getText().toString();
        presenter.checkLogin(emailStr, passStr);
        //presenter.goToHomeRouter();
    }

    /**
     * Metodo que mediante la comprobacion de los valores recibidos por parametros actualiza el layout
     *
     * @param value
     */
    @Override
    public void setErrorLayoutInputs(int value) {
        switch (value) {
            case 0:
                emailTextInputLayout.setError(getResources().getString(R.string.emailLoginError));
                break;
            case 1:
                passTextInputLayout.setError(getResources().getString(R.string.passwordLoginError));
                break;
            case 2:
                emailTextInputLayout.setError(getResources().getString(R.string.emailLoginError));
                passTextInputLayout.setError(getResources().getString(R.string.passwordLoginError));
                break;
            default:
                break;
        }
    }

    private void checkErrors() {
        emailText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    cleanEmailError();
                }
            }
        });

        passText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    cleanPassError();
                }
            }
        });
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
