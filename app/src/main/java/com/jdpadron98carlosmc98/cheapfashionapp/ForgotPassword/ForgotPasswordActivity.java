package com.jdpadron98carlosmc98.cheapfashionapp.ForgotPassword;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.jdpadron98carlosmc98.cheapfashionapp.R;
import com.jdpadron98carlosmc98.cheapfashionapp.app.AppMediator;

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
        if(savedInstanceState == null){
            AppMediator.resetInstance();
        }
        // do the setup
        ForgotPasswordScreen.configure(this);

        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onSendClicked(emailText.getText().toString().trim());
            }
        });
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
public void navigateToNextScreen() {
    Intent intent = new Intent(this, ForgotPasswordActivity.class);
    startActivity(intent);
}

    @Override
    public void displayResult() {
        Toast.makeText(this, "An Email has been sent, check your inbox", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void injectPresenter(ForgotPasswordContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
