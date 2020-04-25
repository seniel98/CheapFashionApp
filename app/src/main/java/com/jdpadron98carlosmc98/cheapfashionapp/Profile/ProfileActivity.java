package com.jdpadron98carlosmc98.cheapfashionapp.Profile;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.jdpadron98carlosmc98.cheapfashionapp.ChangePassword.ChangePasswordActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.Favorite.FavoriteActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.Home.HomeActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.Login.LoginActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.MyProducts.MyProductsActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.R;
import com.jdpadron98carlosmc98.cheapfashionapp.app.AppMediator;

public class ProfileActivity
        extends AppCompatActivity implements ProfileContract.View {

    public static String TAG = ProfileActivity.class.getSimpleName();

    private ProfileContract.Presenter presenter;
    private EditText nameText, emailText, phoneText;
    private TextInputLayout nameTextInputLayout, emailTextInputLayout, phoneTextInputLayout;
    private Button saveProfileButton;
    private TextView changePassText;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //---------------------------------------------//
        initProfileLayout();
        setUpProfileLayout();

        // do the setup
        AppMediator.resetInstance();

        ProfileScreen.configure(this);

        initBottomNavMenu();


        bottomNavigationView.getMenu().getItem(3).setChecked(true);


        changePassText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.goToChangePass();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // load the data
        //presenter.fetchData();
    }

    @Override
    public void onBackPressed() {
        presenter.onBackPressed();
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
        bottomNavigationView = findViewById(R.id.bottomNavViewProfile);
    }

    /**
     * Fijamos los textos del layout
     */
    private void setUpProfileLayout() {
        saveProfileButton.setText(R.string.saveProfileButtonLabel);
        changePassText.setText(R.string.changePassText);
    }

    private void logoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Logout")
                .setMessage("Are you sure?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.callLogout();
                    }
                });

        builder.show();
    }

    private void initBottomNavMenu() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_menu_liked:
                        presenter.goToFavorites();
                        break;
                    case R.id.nav_menu_stuff:
                        presenter.goToMyProducts();
                        break;
                    case R.id.nav_menu_market:
                        presenter.goToHome();
                        break;
                    case R.id.nav_menu_logout:
                        logoutDialog();
                        break;
                }

                return false;
            }
        });
    }

    @Override
    public void goToFavorites() {
        Intent intent = new Intent(this, FavoriteActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void goToMyProducts() {
        Intent intent = new Intent(this, MyProductsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void goToHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void goToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void goChangePass() {
        Intent intent = new Intent(this, ChangePasswordActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
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
