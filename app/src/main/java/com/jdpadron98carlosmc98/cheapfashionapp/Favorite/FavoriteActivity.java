package com.jdpadron98carlosmc98.cheapfashionapp.Favorite;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jdpadron98carlosmc98.cheapfashionapp.Home.HomeActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.Login.LoginActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.MyProducts.MyProductsActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.ProductDetail.ProductDetailActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.Profile.ProfileActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.R;
import com.jdpadron98carlosmc98.cheapfashionapp.app.AppMediator;
import com.jdpadron98carlosmc98.cheapfashionapp.data.ProductItem;

import java.util.List;

public class FavoriteActivity
        extends AppCompatActivity implements FavoriteContract.View {

    public static String TAG = FavoriteActivity.class.getSimpleName();

    private FavoriteContract.Presenter presenter;
    private FavoriteAdapter listAdapter;
    private RecyclerView recyclerView;

    private BottomNavigationView bottomNavigationView;

    private List<ProductItem> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        initLayoutComponents();
        // do the setup
        if (savedInstanceState == null) {
            AppMediator.resetInstance();
        }
        FavoriteScreen.configure(this);

        if (savedInstanceState == null) {
            presenter.onStart();

        } else {
            presenter.onRestart();
        }
   /*     recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        listAdapter = new FavoriteAdapter(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ProductItem item = (ProductItem) view.getTag();
                presenter.selectProductData(item);
            }
        }, list);
*/
        initBottomNavMenu();
        bottomNavigationView.getMenu().getItem(1).setChecked(true);
        //recyclerView.setAdapter(listAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // load the data
        presenter.onResume();

    }

    public void createRecyclerView(){
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        listAdapter = new FavoriteAdapter(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ProductItem item = (ProductItem) view.getTag();
                presenter.selectProductData(item);
            }
        }, list);


        recyclerView.setAdapter(listAdapter);
    }

    private void initLayoutComponents() {
        bottomNavigationView = findViewById(R.id.bottomNavViewFavorite);
        recyclerView = findViewById(R.id.recyclerFavoriteProducts);
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
                    case R.id.nav_menu_profile:
                        presenter.goToProfile();
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
    public void onDataUpdated(FavoriteViewModel viewModel) {
        //Log.e(TAG, "onDataUpdated()");
        // deal with the data
//        ((TextView) findViewById(R.id.data)).setText(viewModel.data);
    }


    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToNextScreen() {
        Intent intent = new Intent(this, FavoriteActivity.class);
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
    public void goToDetail() {
        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void fillArrayList(FavoriteViewModel viewModel) {
        list = viewModel.productItems;
    }


    @Override
    public void injectPresenter(FavoriteContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
