package com.jdpadron98carlosmc98.cheapfashionapp.Home;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jdpadron98carlosmc98.cheapfashionapp.R;
import com.jdpadron98carlosmc98.cheapfashionapp.app.ProductItem;

import java.util.List;

public class HomeActivity
        extends AppCompatActivity implements HomeContract.View {

    public static String TAG = HomeActivity.class.getSimpleName();

    private HomeContract.Presenter presenter;

    private BottomNavigationView bottomNavigationView;

    private FloatingActionButton addProductButton;

    private HomeAdapter homeAdapter;

    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Init Layout components
        initLayoutComponents();

        // do the setup
        HomeScreen.configure(this);


        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        homeAdapter = new HomeAdapter(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        ProductItem item = (ProductItem) view.getTag();
                        presenter.selectProduct(item);
                    }
                }, presenter.getListFromModel());

        recyclerView.setAdapter(homeAdapter);

        initBottomNavMenu();

        bottomNavigationView.getMenu().getItem(0).setChecked(true);

        if (savedInstanceState == null) {
            presenter.onStart();

        } else {
            presenter.onRestart();
        }

        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.goToAddProduct();
            }
        });
    }
    private void logoutDialog(){
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

    private void initLayoutComponents() {
        bottomNavigationView = findViewById(R.id.bottomNavViewMarket);
        addProductButton = findViewById(R.id.floatingButtonMarket);
        recyclerView = findViewById(R.id.homeProductRecyclerView);
    }

    private void initBottomNavMenu() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_menu_liked:
                        presenter.goToFavoritesRouter();
                        break;
                    case R.id.nav_menu_stuff:
                        presenter.goToMyProductsRouter();
                        break;
                    case R.id.nav_menu_profile:
                        presenter.goToProfileRouter();
                        break;
                    case R.id.nav_menu_logout:
                        logoutDialog();
                        break;
                }

                return false;
            }
        });
    }


    public void addItemsFromPresenter(List<ProductItem> productItemList) {
        homeAdapter.addItems(productItemList);
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

        //presenter.onBackPressed();

        finishAffinity();
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
    public void onDataUpdated(HomeViewModel viewModel) {
        //Log.e(TAG, "onDataUpdated()");

        // deal with the data
//        ((TextView) findViewById(R.id.data)).setText(viewModel.data);
    }

    @Override
    public void injectPresenter(HomeContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
