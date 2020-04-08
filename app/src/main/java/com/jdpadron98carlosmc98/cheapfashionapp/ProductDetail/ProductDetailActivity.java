package com.jdpadron98carlosmc98.cheapfashionapp.ProductDetail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jdpadron98carlosmc98.cheapfashionapp.R;

public class ProductDetailActivity
        extends AppCompatActivity implements ProductDetailContract.View {

    public static String TAG = ProductDetailActivity.class.getSimpleName();

    private ProductDetailContract.Presenter presenter;

    private TextView productDetail, productName, productPrice, productSeller;
    private MaterialButton contactProductButton;
    private ImageView productImage;
    private FloatingActionButton productLikeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        //---------------------------------------------//
        initProductDetailLayout();
        setProductDetailLayout();
        // do the setup
        ProductDetailScreen.configure(this);

        if (savedInstanceState == null) {
            presenter.onStart();

        } else {
            presenter.onRestart();
        }
    }

    private void setProductDetailLayout() {

    }

    private void initProductDetailLayout() {
        productDetail = findViewById(R.id.detailProductDetailText);
        productName = findViewById(R.id.productNameProductDetailText);
        productPrice = findViewById(R.id.priceProductDetailText);
        productImage = findViewById(R.id.productImageProductDetail);
        contactProductButton = findViewById(R.id.contactProductDetailButton);
        productLikeButton = findViewById(R.id.productDetailLikeButton);
        productSeller = findViewById(R.id.nameSurnameProductDetailText);

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
    public void onDataUpdated(ProductDetailViewModel viewModel) {
        //Log.e(TAG, "onDataUpdated()");

        // deal with the data
//        ((TextView) findViewById(R.id.data)).setText(viewModel.data);
    }

    @Override
    public void injectPresenter(ProductDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
