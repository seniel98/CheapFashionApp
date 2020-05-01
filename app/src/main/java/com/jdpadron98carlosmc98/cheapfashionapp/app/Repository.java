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

    private static String passwordNotValidMessage = "Password must be at least 8 characters long with alphanumeric format";
    private static String registeredOkMessage = "Registered successfully";

    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private StorageReference storageRef;
    private DatabaseReference usersRef;
    private DatabaseReference productsRef;
    private DatabaseReference databaseReference;

    // private String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.";


    private List<ProductItem> productItemList = new ArrayList<>();


    public static RepositoryContract getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new Repository(context);
        }
        return INSTANCE;
    }


    private Repository(Context context) {
        this.context = context;

        auth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        storageRef = FirebaseStorage.getInstance().getReference();

        usersRef = FirebaseDatabase.getInstance().getReference().child("users");

        productsRef = FirebaseDatabase.getInstance().getReference().child("products");


    }


    @Override
    public void logout(OnLogoutCallback logoutCallback) {
        auth.signOut();
        logoutCallback.onLogout(false);
    }


    @Override
    public void isLoggedIn(OnLoggedInCallback loggedInCallback) {
        if (auth.getCurrentUser() != null) {
            //El usuario tiene la sesion activa
            new JsonTask().execute(JSON_FILE);
            loggedInCallback.onLoggedIn(true);
        } else {
            //El usuario no tiene la sesion activa
            loggedInCallback.onLoggedIn(false);
        }
    }

    @Override
    public void signUp(final UserData userData, final String password, final OnSignUpCallback signUpCallback) {
        if (!isPasswordValid(password)) {

            signUpCallback.onSignUp(true, passwordNotValidMessage);

        } else {
            auth.createUserWithEmailAndPassword(userData.getEmail(), password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                databaseReference.child("users").child(auth.getCurrentUser().getUid()).setValue(userData);
                                signUpCallback.onSignUp(false, registeredOkMessage);

                            } else {

                                signUpCallback.onSignUp(true, task.getException().getMessage());

                            }
                        }
                    });
        }

    }


    /**
     * Metodo para comprobar si la contraseña cumple con los requisitos solicitados.
     * Al menos 8 caracteres, 1 numero, 1 mayuscula, 1 minuscula
     *
     * @param password
     * @return
     */

    private boolean isPasswordValid(String password) {
        boolean valid = true;
        //Expresion regular que hace que la contraseña deba ser alfanumerica
        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
        /**
         * ^                  # incio del string
         * (?=.*[0-9])       # Al menos un digito
         * (?=.*[a-z])       # Al menos una minuscula
         * (?=.*[A-Z])       # Al menos una mayuscula
         * (?=\S+$)          # Espacios en blanco no permitidos
         * .{8,}             # Al menos 8 caracteres
         * $                 # final del string
         * */

        //Comprobamos si la contraseña introducida cumple con los requisitos de nuestra expresion regular
        if (!password.matches(pattern)) {
            valid = false;
        }
        return valid;
    }


    @Override
    public List<ProductItem> getProductList() {
        return productItemList;
    }

    @Override
    public void signIn(String email, String password, final OnSignInCallback callback) {
        auth.signInWithEmailAndPassword(email, password).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Carga el json de firebase con los datos
                            new JsonTask().execute(JSON_FILE);
                            // Sign in success, update UI with the signed-in user's information
                            callback.onSignIn(false);
                        } else {
                            // If sign in fails, display a message to the user.
                            callback.onSignIn(true);
                        }
                    }
                });
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
