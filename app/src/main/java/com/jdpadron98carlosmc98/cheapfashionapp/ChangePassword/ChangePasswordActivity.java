package com.jdpadron98carlosmc98.cheapfashionapp.ChangePassword;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.jdpadron98carlosmc98.cheapfashionapp.R;

public class ChangePasswordActivity
        extends AppCompatActivity implements ChangePasswordContract.View {

    public static String TAG = ChangePasswordActivity.class.getSimpleName();

    private ChangePasswordContract.Presenter presenter;
    private TextInputLayout newPasswordTextInputLayout, repeatPasswordTextInputLayout;

    private EditText newPasswordText, repeatPasswordText;
    private MaterialButton changePasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        //---------------------------------------------//
        initLoginLayout();
        setUpLoginLayout();
        // do the setup
        ChangePasswordScreen.configure(this);

        if (savedInstanceState == null) {
            presenter.onStart();

        } else {
            presenter.onRestart();
        }
    }

    private void setUpLoginLayout() {
        changePasswordButton.setText(R.string.changePasswordButtonLabel);
    }

    private void initLoginLayout() {
        newPasswordTextInputLayout = findViewById(R.id.newPasswordChangePasswordTextInputLayout);
        newPasswordText = findViewById(R.id.newPasswordChangePasswordText);
        changePasswordButton = findViewById(R.id.changePassButton);
        repeatPasswordText = findViewById(R.id.repeatPasswordChangePasswordText);
        repeatPasswordTextInputLayout = findViewById(R.id.repeatPasswordChangePasswordTextInputLayout);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // load the data
        presenter.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        presenter.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();

        presenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.onDestroy();
    }

    @Override
    public void onDataUpdated(ChangePasswordViewModel viewModel) {
        //Log.e(TAG, "onDataUpdated()");

        // deal with the data
//        ((TextView) findViewById(R.id.data)).setText(viewModel.data);
    }

    @Override
    public void injectPresenter(ChangePasswordContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
