package com.jdpadron98carlosmc98.cheapfashionapp.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.jdpadron98carlosmc98.cheapfashionapp.R;

public class ProfileActivity
        extends AppCompatActivity implements ProfileContract.View {

    public static String TAG = ProfileActivity.class.getSimpleName();

    private ProfileContract.Presenter presenter;
    private EditText nameText,emailText,phoneText;
    private TextInputLayout nameTextInputLayout,emailTextInputLayout,phoneTextInputLayout;
    private Button saveProfileButton;
    private TextView changePassText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //---------------------------------------------//
        initProfileLayout();
        setUpProfileLayout();

        // do the setup
        ProfileScreen.configure(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // load the data
        //presenter.fetchData();
    }

    /**
     * Inicializamos los distintos elementos de la vista
     */
    private void initProfileLayout() {
        nameText = findViewById(R.id.nameProfileText);
        emailText = findViewById(R.id.emailProfileText);
        phoneText = findViewById(R.id.phoneNumberProfileText);
        changePassText = findViewById(R.id.changePassText);
        nameTextInputLayout = findViewById(R.id.nameProfileTextInputLayout);
        emailTextInputLayout = findViewById(R.id.emailProfileTextInputLayout);
        phoneTextInputLayout = findViewById(R.id.phoneNumberProfileTextInputLayout);
        saveProfileButton = findViewById(R.id.saveProfileButton);
    }
    /**
     * Fijamos los textos del layout
     */
    private void setUpProfileLayout() {
        saveProfileButton.setText(R.string.saveProfileButtonLabel);
        changePassText.setText(R.string.changePassText);
    }

/*
    @Override
    public void displayData(ProfileViewModel viewModel) {
        //Log.e(TAG, "displayData()");

        // deal with the data
        ((TextView) findViewById(R.id.data)).setText(viewModel.data);
    }
*/
    @Override
    public void injectPresenter(ProfileContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
