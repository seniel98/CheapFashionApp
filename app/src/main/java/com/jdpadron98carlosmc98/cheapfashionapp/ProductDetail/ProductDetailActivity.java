package com.jdpadron98carlosmc98.cheapfashionapp.ProductDetail;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jdpadron98carlosmc98.cheapfashionapp.AddProduct.AddProductActivity;
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
    private void selectContact(){
        final CharSequence[] items= {"E-mail", "Phone number", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Contact with seller");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(items[i].equals("E-mail")){
                    sendEmail();
                }else if(items[i].equals("Phone number")){
                    callUser();
                }else if(items[i].equals("Cancel")){
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public void sendEmail(){
        String email = "mdbviewerandeditor@gmail.com";
        String subject = "Don't have user ID";
        String body = "";
        String chooserTitle = "Choose your preferred app";
        Uri uri = Uri.parse("mailto:" + email)
                .buildUpon()
                .appendQueryParameter("To", email)
                .appendQueryParameter("subject", subject)
                .appendQueryParameter("body", body)
                .build();

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, uri);
        startActivity(Intent.createChooser(emailIntent, chooserTitle));
    }

    public void callUser(){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:123456789"));
        startActivity(intent);
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
