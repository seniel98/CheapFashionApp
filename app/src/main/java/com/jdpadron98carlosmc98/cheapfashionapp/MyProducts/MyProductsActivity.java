package com.jdpadron98carlosmc98.cheapfashionapp.MyProducts;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jdpadron98carlosmc98.cheapfashionapp.R;
import com.jdpadron98carlosmc98.cheapfashionapp.app.ProductItem;

public class MyProductsActivity
        extends AppCompatActivity implements MyProductsContract.View {

    public static String TAG = MyProductsActivity.class.getSimpleName();

    private MyProductsContract.Presenter presenter;

    private MyProductsAdapter myProductsAdapter;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_products);
        getSupportActionBar().setTitle(R.string.app_name);

        recyclerView = findViewById(R.id.myProductsProductRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        myProductsAdapter = new MyProductsAdapter(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ProductItem item = (ProductItem) view.getTag();
                presenter.selectProduct(item);
            }
        });

        recyclerView.setAdapter(myProductsAdapter);


        // do the setup
        MyProductsScreen.configure(this);

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
