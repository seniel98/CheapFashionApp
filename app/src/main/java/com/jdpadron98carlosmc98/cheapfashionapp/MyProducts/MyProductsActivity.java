package com.jdpadron98carlosmc98.cheapfashionapp.MyProducts;

import android.content.DialogInterface;
import android.content.Intent;
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
import com.jdpadron98carlosmc98.cheapfashionapp.AddProduct.AddProductActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.Favorite.FavoriteActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.Home.HomeActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.Login.LoginActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.Profile.ProfileActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.R;
import com.jdpadron98carlosmc98.cheapfashionapp.app.AppMediator;
import com.jdpadron98carlosmc98.cheapfashionapp.app.ProductItem;

import java.util.List;

public class MyProductsActivity
        extends AppCompatActivity implements MyProductsContract.View {

    public static String TAG = MyProductsActivity.class.getSimpleName();

    private MyProductsContract.Presenter presenter;

    private MyProductsAdapter myProductsAdapter;

    private RecyclerView recyclerView;

    private BottomNavigationView bottomNavigationView;

    private FloatingActionButton addProductButton;

    private List<ProductItem> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_products);

        initLayoutComponents();

        AppMediator.resetInstance();

        MyProductsScreen.configure(this);

        if (savedInstanceState == null) {
            presenter.onStart();

        } else {
            presenter.onRestart();
        }

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        myProductsAdapter = new MyProductsAdapter(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ProductItem item = (ProductItem) view.getTag();
                presenter.selectProduct(item);
            }
        }, list);

        recyclerView.setAdapter(myProductsAdapter);


        initBottomNavMenu();

        bottomNavigationView.getMenu().getItem(2).setChecked(true);


        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.goToAddProduct();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // load the data
        presenter.onResume();
    }

    private void initLayoutComponents() {
        bottomNavigationView = findViewById(R.id.bottomNavViewMyProducts);
        addProductButton = findViewById(R.id.floatingButtonMyProducts);
        recyclerView = findViewById(R.id.myProductsProductRecyclerView);
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
                    case R.id.nav_menu_profile:
                        presenter.goToProfile();
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
    public void onBackPressed() {
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
    public void onDataUpdated(MyProductsViewModel viewModel) {
        //Log.e(TAG, "onDataUpdated()");

        // deal with the data
        //((TextView) findViewById(R.id.data)).setText(viewModel.data);
    }

    @Override
    public void fillArrayList(MyProductsViewModel viewModel) {
        list = viewModel.list;
    }

    @Override
    public void injectPresenter(MyProductsContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void goToFavorites() {
        Intent intent = new Intent(this, FavoriteActivity.class);
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
    public void goToProfile() {
        Intent intent = new Intent(this, ProfileActivity.class);
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
    public void goAddProduct() {
        Intent intent = new Intent(this, AddProductActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
