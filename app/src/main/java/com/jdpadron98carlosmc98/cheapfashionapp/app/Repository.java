package com.jdpadron98carlosmc98.cheapfashionapp.app;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Repository implements RepositoryContract {

    private static Repository INSTANCE;
    private Context context;
    public static String TAG = Repository.class.getSimpleName();

    public static final String JSON_FILE = "https://cheap-fashion-app.firebaseio.com/.json";
    public static final String JSON_ROOT = "defaultProducts";


    private FirebaseAuth auth;
    private FirebaseUser user;

    private String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.";


    private StorageReference storageRef;
    private DatabaseReference usersRef;
    private DatabaseReference productsRef;
    private DatabaseReference databaseReference;

    private List<ProductItem> productItemList = new ArrayList<>();
   /* private UserData userData1 = new UserData("name1", "email1", "phone1");
    private UserData userData2 = new UserData("name2", "email2", "phone2");
    private UserData userData3 = new UserData("name3", "email3", "phone3");
    private UserData userData4 = new UserData("name4", "email4", "phone4");
    private UserData userData5 = new UserData("name5", "email5", "phone5");
    private UserData userData6 = new UserData("name6", "email6", "phone6");*/


    public static RepositoryContract getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new Repository(context);
        }
        return INSTANCE;
    }


    private Repository(Context context) {
        this.context = context;

        auth = FirebaseAuth.getInstance();

        user = auth.getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        storageRef = FirebaseStorage.getInstance().getReference();

        usersRef = FirebaseDatabase.getInstance().getReference().child("users");

        productsRef = FirebaseDatabase.getInstance().getReference().child("products");

  /*      productItemList.add(productItem1);
        productItemList.add(productItem2);
        productItemList.add(productItem3);
        productItemList.add(productItem4);
        productItemList.add(productItem5);
        productItemList.add(productItem6);*/
        new JsonTask().execute(JSON_FILE);

    }


    @Override
    public void createUser(final UserData userData, String password, final RegisterCallback registerCallback) {
        auth.createUserWithEmailAndPassword(userData.getEmail(), password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            databaseReference.child("users").child(auth.getCurrentUser().getUid()).setValue(userData);
                            registerCallback.createUserError(false, "Registered succesfully");
                        } else {
                            registerCallback.createUserError(true, task.getException().getMessage());
                        }
                    }
                });
    }


    @Override
    public List<ProductItem> getProductList() {
        return productItemList;
    }


    /**
     * Load the JSON file from the assets to have a default catalog
     *
     * @return
     */
    private String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = context.getAssets().open(JSON_FILE);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException error) {
        }
        return json;
    }


    private boolean loadCatalogFromJSON(String json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray(JSON_ROOT);
            if (jsonArray.length() > 0) {
                List<ProductItem> productItems = Arrays.asList(gson.fromJson(jsonArray.toString(), ProductItem[].class));
                productItemList.addAll(productItems);
                Log.e(TAG, "loadCatalogFromJSON.productItem" + productItemList.get(0).name);
                return true;
            }
        } catch (JSONException error) {
        }
        return false;
    }


    private class JsonTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();


                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                    Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)

                }

                return buffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            loadCatalogFromJSON(result);
        }
    }
}
