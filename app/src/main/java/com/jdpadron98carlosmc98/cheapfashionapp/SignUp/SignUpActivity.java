package com.jdpadron98carlosmc98.cheapfashionapp.SignUp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.jdpadron98carlosmc98.cheapfashionapp.R;
import com.jdpadron98carlosmc98.cheapfashionapp.app.AppMediator;

public class SignUpActivity
        extends AppCompatActivity implements SignUpContract.View {

    public static String TAG = SignUpActivity.class.getSimpleName();

    private SignUpContract.Presenter presenter;
    private TextInputLayout nameTextInputLayout, emailTextInputLayout, phoneTextInputLayout,
            passTextInputLayout;
    private EditText nameText, emailText, phoneText, passText;
    private MaterialButton signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //---------------------------------------------//
        initSignUpLayout();
        setSignUpLayout();

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString().trim();
                String email = emailText.getText().toString().trim();
                String phone = phoneText.getText().toString().trim();
                String pass = passText.getText().toString().trim();
                presenter.signUpClicked(name,email,phone,pass);
            }
        });
        // do the setup
        if(savedInstanceState == null){
            AppMediator.resetInstance();
        }

        SignUpScreen.configure(this);
    }

    /**
     * Inicializamos los distintos elementos de la vista
     */
    private void initSignUpLayout() {
        nameText = findViewById(R.id.nameSignUpText);
        emailText = findViewById(R.id.emailSignUpText);
        phoneText = findViewById(R.id.phoneNumberSignUpText);
        passText = findViewById(R.id.passSignUpText);
        nameTextInputLayout = findViewById(R.id.nameSignUpTextInputLayout);
        emailTextInputLayout = findViewById(R.id.emailSignUpTextInputLayout);
        phoneTextInputLayout = findViewById(R.id.phoneNumberSignUpTextInputLayout);
        passTextInputLayout = findViewById(R.id.passSignUpTextInputLayout);
        signUpButton = findViewById(R.id.signUpButton);
    }

    /**
     * Fijamos los textos del layout
     */
    private void setSignUpLayout() {
        signUpButton.setText(R.string.signUpButtonLabel);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // load the data
//        presenter.fetchData();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }





    /*    @Override
    public void displayData(SignUpViewModel viewModel) {
        //Log.e(TAG, "displayData()");

        // deal with the data
    }*/

    @Override
    public void injectPresenter(SignUpContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
