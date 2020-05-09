package com.jdpadron98carlosmc98.cheapfashionapp.Home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jdpadron98carlosmc98.cheapfashionapp.AddProduct.AddProductActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.Favorite.FavoriteActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.Login.LoginActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.MyProducts.MyProductsActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.ProductDetail.ProductDetailActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.Profile.ProfileActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.R;
import com.jdpadron98carlosmc98.cheapfashionapp.app.AppMediator;
import com.jdpadron98carlosmc98.cheapfashionapp.data.ProductItem;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class HomeActivity
        extends AppCompatActivity implements HomeContract.View {

    public static String TAG = HomeActivity.class.getSimpleName();

    private HomeContract.Presenter presenter;

    private BottomNavigationView bottomNavigationView;

    private FloatingActionButton addProductButton;

    private HomeAdapter homeAdapter;

    private RecyclerView recyclerView;

    private List<ProductItem> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Init Layout components
        initLayoutComponents();

        // do the setup
        if (savedInstanceState == null) {
            AppMediator.resetInstance();
        }
        HomeScreen.configure(this);

        if (savedInstanceState == null) {
            presenter.onStart();

        } else {
            presenter.onRestart();
        }


        list = new ArrayList<>();

        presenter.downloadDataFromRepository();

        //presenter.loadFavoriteList();

        createRecyclerView();

        initBottomNavMenu();

        bottomNavigationView.getMenu().getItem(0).setChecked(true);


        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.goToAddProduct();
            }
        });
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
                        presenter.goToFavorites();
                        break;
                    case R.id.nav_menu_stuff:
                        presenter.goToMyProducts();
                        break;
                    case R.id.nav_menu_profile:
                        presenter.goToProfile();
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

    @Override
    public void goToDetail() {
        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void navigateToNextScreen() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();


        // load the data
        presenter.onResume();


    }

    private void createRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        homeAdapter = new HomeAdapter(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        ProductItem item = (ProductItem) view.getTag();
                        Log.e(TAG, "HomeAdapter.item " + item.getPicture());
                        presenter.selectProduct(item);
                    }
                }, list);

        recyclerView.setAdapter(homeAdapter);
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
    public void showToast(String msg) {
        final Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, 1000);

    }

    @Override
    public void onDataUpdated(HomeViewModel viewModel) {
        //Log.e(TAG, "onDataUpdated()");

        // deal with the data
//        ((TextView) findViewById(R.id.data)).setText(viewModel.data);
    }

    @Override
    public void fillArrayList(final HomeViewModel viewModel) {
        //Ejecutamos la accion de añadir los items en el hilo especifico para la interfaz grafica
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // deal with the data
                homeAdapter.setItems(viewModel.homeProductList);
            }
        });
    }

    @Override
    public void injectPresenter(HomeContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
