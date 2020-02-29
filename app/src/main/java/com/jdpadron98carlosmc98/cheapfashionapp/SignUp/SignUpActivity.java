package com.jdpadron98carlosmc98.cheapfashionapp.SignUp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jdpadron98carlosmc98.cheapfashionapp.R;

public class SignUpActivity
        extends AppCompatActivity implements SignUpContract.View {

    public static String TAG = SignUpActivity.class.getSimpleName();

    private SignUpContract.Presenter presenter;
    private TextView nameText,emailText,phoneText,passText;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initSignUpLayout();
        setSignUpLayout();
        // do the setup
        SignUpScreen.configure(this);
    }

    /**
     * Inicializamos los distintos elementos de la vista
     */
    private void initSignUpLayout(){
        nameText = findViewById(R.id.nameSignUpText);
        emailText = findViewById(R.id.emailSignUpText);
        phoneText = findViewById(R.id.phoneNumberSignUpText);
        passText = findViewById(R.id.passSignUpText);
        signUpButton = findViewById(R.id.signUpButton);
    }

    /**
     * Fijamos los textos del layout
     */
    private void setSignUpLayout(){
        signUpButton.setText(R.string.signUpButtonLabel);
    }
    @Override
    protected void onResume() {
        super.onResume();

        // load the data
//        presenter.fetchData();
    }

    @Override
    public void displayData(SignUpViewModel viewModel) {
        //Log.e(TAG, "displayData()");

        // deal with the data
    }

    @Override
    public void injectPresenter(SignUpContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
