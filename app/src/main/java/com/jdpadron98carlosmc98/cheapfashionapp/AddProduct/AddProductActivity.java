package com.jdpadron98carlosmc98.cheapfashionapp.AddProduct;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.jdpadron98carlosmc98.cheapfashionapp.R;

public class AddProductActivity
        extends AppCompatActivity implements AddProductContract.View {

    public static String TAG = AddProductActivity.class.getSimpleName();

    private AddProductContract.Presenter presenter;

    private ImageView addProductImage;
    private TextView addProductAddImageText;
    private Integer REQUEST_CAMERA=1,SELECT_FILE=0;
    private EditText productEditText, priceEditText, descriptionEditText;
    private TextInputLayout productInputLayout, priceInputLayout, descriptionInputLayout;
    private MaterialButton addButton;
    private Uri filePath;
    private final int READ_EXTERNAL_STORAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        initLayoutComponents();
        initLayoutData();
        askPermission(Manifest.permission.READ_EXTERNAL_STORAGE,READ_EXTERNAL_STORAGE);

        // do the setup
        AddProductScreen.configure(this);

        if (savedInstanceState == null) {
            presenter.onStart();

        } else {
            presenter.onRestart();
        }

        addProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
    }

    private void selectImage(){
        final CharSequence[] items= {"Camera", "Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(AddProductActivity.this);
        builder.setTitle("Add Image");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(items[i].equals("Camera")){
                    Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,REQUEST_CAMERA);

                }else if(items[i].equals("Gallery")){
                    Intent intent= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent.createChooser(intent,"Select File"),SELECT_FILE);
                }else if(items[i].equals("Cancel")){
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void askPermission(String permission, int requestCode) {
        if(ContextCompat.checkSelfPermission(this, permission)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
        }else{

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode== Activity.RESULT_OK){
            if(requestCode==REQUEST_CAMERA){
                Bundle bundle= data.getExtras();
                final Bitmap bitmap=(Bitmap) bundle.get("data");
                addProductImage.setImageBitmap(bitmap);
            }else if(requestCode== SELECT_FILE){
                Uri selectedImage= data.getData();
                filePath= selectedImage;
                addProductImage.setImageURI(selectedImage);
            }
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