package com.jdpadron98carlosmc98.cheapfashionapp.Favorite;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jdpadron98carlosmc98.cheapfashionapp.R;
import com.jdpadron98carlosmc98.cheapfashionapp.app.ProductItem;

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
                        presenter.goToProfileRouter();
                        break;
                    case R.id.nav_menu_stuff:
                        presenter.goToMyProductsRouter();
                        break;
                    case R.id.nav_menu_market:
                        presenter.goToHomeRouter();
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
    public void onDataUpdated(FavoriteViewModel viewModel) {
        //Log.e(TAG, "onDataUpdated()");
        // deal with the data
//        ((TextView) findViewById(R.id.data)).setText(viewModel.data);
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
