package com.jdpadron98carlosmc98.cheapfashionapp.ChangePassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.jdpadron98carlosmc98.cheapfashionapp.R;
import com.jdpadron98carlosmc98.cheapfashionapp.app.AppMediator;

public class ChangePasswordActivity
        extends AppCompatActivity implements ChangePasswordContract.View {

    public static String TAG = ChangePasswordActivity.class.getSimpleName();

    private ChangePasswordContract.Presenter presenter;
    private TextInputLayout newPasswordTextInputLayout, repeatPasswordTextInputLayout;

    private EditText currentPasswordText, newPasswordText;
    private MaterialButton changePasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        //---------------------------------------------//
        initLoginLayout();
        setUpLoginLayout();
        // do the setup
        if(savedInstanceState == null){
            AppMediator.resetInstance();
        }
        ChangePasswordScreen.configure(this);

        if (savedInstanceState == null) {
            presenter.onStart();

        } else {
            presenter.onRestart();
        }
        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onSaveClicked(currentPasswordText.getText().toString().trim(),
                        newPasswordText.getText().toString().trim());
            }
        });
    }

    private void setUpLoginLayout() {
        changePasswordButton.setText(R.string.changePasswordButtonLabel);
    }

    private void initLoginLayout() {
        newPasswordTextInputLayout = findViewById(R.id.newPasswordChangePasswordTextInputLayout);
        currentPasswordText = findViewById(R.id.currentPasswordChangePasswordText);
        changePasswordButton = findViewById(R.id.changePassButton);
        newPasswordText = findViewById(R.id.newPasswordChangePasswordText);
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
    public void navigateToNextScreen() {
        Intent intent = new Intent(this, ChangePasswordActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void displayResult(ChangePasswordViewModel viewModel) {
        Toast.makeText(this, viewModel.message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void injectPresenter(ChangePasswordContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
