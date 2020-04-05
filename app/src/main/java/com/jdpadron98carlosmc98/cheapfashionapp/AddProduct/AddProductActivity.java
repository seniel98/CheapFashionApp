package com.jdpadron98carlosmc98.cheapfashionapp.AddProduct;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.jdpadron98carlosmc98.cheapfashionapp.R;

public class AddProductActivity
        extends AppCompatActivity implements AddProductContract.View {

    public static String TAG = AddProductActivity.class.getSimpleName();

    private AddProductContract.Presenter presenter;

    private ImageView addProductImage;
    private TextView addProductAddImageText;
    private EditText productEditText, priceEditText, descriptionEditText;
    private TextInputLayout productInputLayout, priceInputLayout, descriptionInputLayout;
    private MaterialButton addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        getSupportActionBar().setTitle(R.string.app_name);

        initLayoutComponents();
        initLayoutData();
        // do the setup
        AddProductScreen.configure(this);

        if (savedInstanceState == null) {
            presenter.onStart();

        } else {
            presenter.onRestart();
        }
    }


    private void initLayoutComponents() {
        addProductImage = findViewById(R.id.addProductImage);
        addProductAddImageText = findViewById(R.id.addProductAddImageText);
        productInputLayout = findViewById(R.id.addProductProductTextInputLayout);
        priceInputLayout = findViewById(R.id.addProductPriceTextInputLayout);
        descriptionInputLayout = findViewById(R.id.addProductProductDescriptionInputLayout);
        productEditText = findViewById(R.id.addProductProductText);
        priceEditText = findViewById(R.id.addProductPriceText);
        descriptionEditText = findViewById(R.id.addProductDescriptionText);
        addButton = findViewById(R.id.addProductAddButton);
    }


    private void initLayoutData() {
        addProductAddImageText.setText(getResources().getString(R.string.addProductAddImageText));
        addButton.setText(getResources().getString(R.string.addProductAddButtonLabel));
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
    public void onDataUpdated(AddProductViewModel viewModel) {
        //Log.e(TAG, "onDataUpdated()");

        // deal with the data
        //((TextView) findViewById(R.id.data)).setText(viewModel.data);
    }

    @Override
    public void injectPresenter(AddProductContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
