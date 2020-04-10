package com.jdpadron98carlosmc98.cheapfashionapp.ProductDetail;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jdpadron98carlosmc98.cheapfashionapp.AddProduct.AddProductActivity;
import com.jdpadron98carlosmc98.cheapfashionapp.R;
import com.jdpadron98carlosmc98.cheapfashionapp.app.ProductItem;

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
        contactProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.initDialog();
            }
        });

        productLikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.likeButtonPressed();
            }
        });
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

    public void selectContact(final ProductDetailViewModel viewModel){
        final CharSequence[] items= {"E-mail", "Phone number", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Contact with seller");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(items[i].equals("E-mail")){
                    sendEmail(viewModel);
                }else if(items[i].equals("Phone number")){
                    callUser(viewModel);
                }else if(items[i].equals("Cancel")){
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void setLikedButtonDisabled() {
        productLikeButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_border_24dp));
    }

    @Override
    public void setLikeButtonEnabled() {
        productLikeButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_24px));
    }

    public void sendEmail(ProductDetailViewModel viewModel){
        String emailSender = viewModel.item.getUserData().getEmail();
        String email = emailSender;
        String subject = "Im interested in one of your products";
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

    @Override
    public void callUser(ProductDetailViewModel viewModel) {
        String phoneNumber = viewModel.item.getUserData().getPhoneNumber();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+phoneNumber));
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
    public void displayProductData(ProductDetailState state) {
        ProductItem item = state.item;
        productImage.setImageResource(item.getDrawable());
        productSeller.setText(item.getUserData().getName());
        productPrice.setText(item.getPrice());
        productDetail.setText(item.getDetail());
        productName.setText(item.getName());
        setImageFloatingButton(item.liked);
    }

    private void setImageFloatingButton(boolean liked){
        if(liked){
            setLikeButtonEnabled();
        }else{
            setLikedButtonDisabled();
        }
    }
    @Override
    public void injectPresenter(ProductDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
