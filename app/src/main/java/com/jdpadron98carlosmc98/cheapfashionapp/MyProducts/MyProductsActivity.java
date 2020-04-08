package com.jdpadron98carlosmc98.cheapfashionapp.MyProducts;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jdpadron98carlosmc98.cheapfashionapp.R;
import com.jdpadron98carlosmc98.cheapfashionapp.app.ProductItem;

public class MyProductsActivity
        extends AppCompatActivity implements MyProductsContract.View {

    public static String TAG = MyProductsActivity.class.getSimpleName();

    private MyProductsContract.Presenter presenter;

    private MyProductsAdapter myProductsAdapter;

    private RecyclerView recyclerView;

    private BottomNavigationView bottomNavigationView;

    private FloatingActionButton addProductButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_products);

        initLayoutComponents();

        MyProductsScreen.configure(this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        myProductsAdapter = new MyProductsAdapter(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ProductItem item = (ProductItem) view.getTag();
                presenter.selectProduct(item);
            }
        });

        recyclerView.setAdapter(myProductsAdapter);


        initBottomNavMenu();

        bottomNavigationView.getMenu().getItem(2).setChecked(true);


        if (savedInstanceState == null) {
            presenter.onStart();

        } else {
            presenter.onRestart();
        }
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

    private void initBottomNavMenu() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_menu_liked:
                        presenter.goToFavoritesRouter();
                        break;
                    case R.id.nav_menu_profile:
                        presenter.goToProfileRouter();
                        break;
                    case R.id.nav_menu_market:
                        presenter.goToHomeRouter();
                        break;
                    case R.id.nav_menu_logout:
                        presenter.callLogout();
                        break;
                }

                return false;
            }
        });
        /*BottomNavigationView.OnNavigationItemSelectedListener navListener =
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    //Checks which item is selected to then call presenter method
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.nav_menu_liked:
                                presenter.goToFavoritesRouter();
                                break;
                            case R.id.nav_menu_market:
                                presenter.goToHomeRouter();
                                break;
                            case R.id.nav_menu_profile:
                                presenter.goToProfileRouter();
                                break;
                            case R.id.nav_menu_logout:
                                presenter.callLogout();
                                break;
                        }
                        return true;
                    }

                };

        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);*/
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
    public void onDataUpdated(MyProductsViewModel viewModel) {
        //Log.e(TAG, "onDataUpdated()");

        // deal with the data
        //((TextView) findViewById(R.id.data)).setText(viewModel.data);
    }

    @Override
    public void injectPresenter(MyProductsContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
