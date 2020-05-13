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
import com.jdpadron98carlosmc98.cheapfashionapp.data.UserData;

public class SignUpActivity
        extends AppCompatActivity implements SignUpContract.View {

    public static String TAG = SignUpActivity.class.getSimpleName();

    private SignUpContract.Presenter presenter;
    private TextInputLayout nameTextInputLayout, emailTextInputLayout, phoneTextInputLayout,
            passTextInputLayout;
    private Toast toast;
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
                UserData userData = new UserData(name,email,phone);
                presenter.signUpClicked(userData, pass);
            }
        });
        // do the setup
        if (savedInstanceState == null) {
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
    public void showToast(SignUpViewModel msg) {
        //Reiniciamos el toast para que no se generen infinitos toasts
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(this, msg.message, Toast.LENGTH_SHORT);
        toast.show();


    }



    @Override
    public void injectPresenter(SignUpContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
