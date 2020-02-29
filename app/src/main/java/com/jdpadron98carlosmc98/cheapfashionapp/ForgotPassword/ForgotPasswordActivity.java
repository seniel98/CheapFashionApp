package com.jdpadron98carlosmc98.cheapfashionapp.ForgotPassword;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.jdpadron98carlosmc98.cheapfashionapp.R;

public class ForgotPasswordActivity
        extends AppCompatActivity implements ForgotPasswordContract.View {

    public static String TAG = ForgotPasswordActivity.class.getSimpleName();

    private ForgotPasswordContract.Presenter presenter;

    private MaterialButton sendEmailButton;

    private TextInputLayout emailTextInputLayout;

    private EditText emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        //---------------------------------------------//
        initLoginLayout();
        setUpLoginLayout();

        // do the setup
        ForgotPasswordScreen.configure(this);
    }


    /**
     * Inicializamos los distintos elementos de la vista
     */
    private void initLoginLayout() {
        sendEmailButton = findViewById(R.id.sendPassEmailButton);
        emailTextInputLayout = findViewById(R.id.emailTextInputLayoutForgotPass);
        emailText = findViewById(R.id.emailForgotPassText);
    }

    /**
     * Fijamos los textos del layout
     */
    private void setUpLoginLayout() {
        sendEmailButton.setText(R.string.sendButtonLabelForgotPass);

    }

    @Override
    protected void onResume() {
        super.onResume();

        // load the data
        //presenter.fetchData();
    }
/*
    @Override
    public void displayData(ForgotPasswordViewModel viewModel) {
        //Log.e(TAG, "displayData()");

        // deal with the data
    }*/

    @Override
    public void injectPresenter(ForgotPasswordContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
