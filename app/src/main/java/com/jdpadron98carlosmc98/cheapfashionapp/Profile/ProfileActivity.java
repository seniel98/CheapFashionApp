package com.jdpadron98carlosmc98.cheapfashionapp.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.jdpadron98carlosmc98.cheapfashionapp.R;

public class ProfileActivity
        extends AppCompatActivity implements ProfileContract.View {

    public static String TAG = ProfileActivity.class.getSimpleName();

    private ProfileContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // do the setup
        ProfileScreen.configure(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // load the data
        //presenter.fetchData();
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
