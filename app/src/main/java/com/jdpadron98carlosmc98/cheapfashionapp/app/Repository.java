package com.jdpadron98carlosmc98.cheapfashionapp.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class Repository implements RepositoryContract {

    private static Repository INSTANCE;
    private Context context;
    public static String TAG = Repository.class.getSimpleName();

    public static final String JSON_FILE = "https://cheap-fashion-app.firebaseio.com/products/.json";
    public static final String JSON_ROOT = "products";
    public static final String JSON_FAVORITE = "https://cheap-fashion-app.firebaseio.com/favorite/.json";


    private static String passwordNotValidMessage = "Password must be at least 8 characters long with alphanumeric format";
    private static String registeredOkMessage = "Registered successfully";

    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private StorageReference storageRef;
    private DatabaseReference usersRef;
    private DatabaseReference productsRef;
    private DatabaseReference favoriteRef;
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

        favoriteRef = FirebaseDatabase.getInstance().getReference().child("favoriteProducts");

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
            loggedInCallback.onLoggedIn(true);
        } else {
            //El usuario no tiene la sesion activa
            loggedInCallback.onLoggedIn(false);
        }
    }

    @Override
    public void addNewProduct(final String productName, final String productPrice, final String productDescription,
                              ImageView imageView, final CreateProductEntryCallBack callback) {

        //final UserData user = getUserDataFromFirebase();
        final UserData[] user = new UserData[1];
        //databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(auth.getCurrentUser().getUid());
        final List<ProductItem> productItems = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //Pillamos un tipo generico segun las recomendaciones de Firebase, ver https://firebase.googleblog.com/2014/04/best-practices-arrays-in-firebase.html
                GenericTypeIndicator<List<ProductItem>> genericTypeIndicator = new GenericTypeIndicator<List<ProductItem>>() {
                };
                List<ProductItem> productList = dataSnapshot.child("products").child(auth.getCurrentUser().getUid()).getValue(genericTypeIndicator);
                //Log.e(TAG,"productList"+ dataSnapshot.child("products").child(auth.getCurrentUser().getUid()).getValue(genericTypeIndicator));

                if (productList != null) {
                    productItems.addAll(productList);
                }


                String name = dataSnapshot.child("users").child(auth.getCurrentUser().getUid()).child("name").getValue(String.class);
                String email = dataSnapshot.child("users").child(auth.getCurrentUser().getUid()).child("email").getValue(String.class);
                String phoneNumber = dataSnapshot.child("users").child(auth.getCurrentUser().getUid()).child("phoneNumber").getValue(String.class);
                user[0] = new UserData(name, email, phoneNumber);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        byte[] data = convertImageView(imageView);
        final StorageReference ref = storageRef.child("images/" + auth.getCurrentUser().getUid() +
                "/" + productName + ".jpg");
        UploadTask uploadTask = ref.putBytes(data);
        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot,
                Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                // Continue with the task to get the download URL
                return ref.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    String url = downloadUri.toString();//Se obtiene la direccion de la imagen que se acaba de subir
                    final String uuid = UUID.randomUUID().toString().replace("-", "");
                    ProductItem productItem = new ProductItem(uuid, productPrice, productName, url, productDescription, user[0]);
                    productItems.add(productItem);
                    productsRef.child(auth.getCurrentUser().getUid()).setValue(productItems);
                    callback.onAddNewProduct(false);
                }
            }
        });
    }

    @Override
    public void forgetPassword(String email, final onForgetPasswordCallback callback) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    callback.onForgetPassword(false);
                }
            }
        });
    }

    @Override
    public void changePassword(String currentPassword, final String newPassword, final onChangePasswordCallback callback) {
        final FirebaseUser user = auth.getCurrentUser();
        String email = auth.getCurrentUser().getEmail();

        AuthCredential credential = EmailAuthProvider
                .getCredential(email, currentPassword);

// Prompt the user to re-provide their sign-in credentials
        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            if (isPasswordValid(newPassword)) {

                                user.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Log.d(TAG, "Password updated");
                                            callback.onChangePassword(false, "Password updated");
                                        } else {
                                            Log.d(TAG, "Error password not updated");
                                            callback.onChangePassword(true, "Error password not updated");
                                        }
                                    }
                                });
                            } else {
                                callback.onChangePassword(true, "new password is not valid");
                            }
                        } else {
                            Log.d(TAG, "Error auth failed");
                            callback.onChangePassword(true, "current password is wrong");
                        }
                    }
                });
    }

    @Override
    public void addFavoriteProduct(final ProductItem productItem, final CreateFavoriteProductEntryCallBack callback) {
        final List<ProductItem> favoriteProducts = new ArrayList<>();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Pillamos un tipo generico segun las recomendaciones de Firebase, ver https://firebase.googleblog.com/2014/04/best-practices-arrays-in-firebase.html
                GenericTypeIndicator<List<ProductItem>> genericTypeIndicator = new GenericTypeIndicator<List<ProductItem>>() {
                };
                List<ProductItem> productList = dataSnapshot.child("favorite").child(auth.getCurrentUser().getUid()).getValue(genericTypeIndicator);
                //Log.e(TAG,"productList"+ dataSnapshot.child("products").child(auth.getCurrentUser().getUid()).getValue(genericTypeIndicator));

                if (productList != null) {
                    favoriteProducts.addAll(productList);
                }

                ProductItem favorite = new ProductItem(productItem.pid, productItem.price, productItem.name,
                        productItem.picture, productItem.detail, productItem.userData);
                if (favoriteProducts.size() == 0) {
                    favoriteProducts.add(favorite);
                    databaseReference.child("favorite").child(auth.getCurrentUser().getUid()).setValue(favoriteProducts);
                    callback.onAddFavoriteProduct(false);
                } else {
                    for (ProductItem product : favoriteProducts) {
                        if (product.getPid().equals(favorite.pid)) {
                            callback.onAddFavoriteProduct(true);
                            return;
                        }
                    }
                    favoriteProducts.add(favorite);
                    databaseReference.child("favorite").child(auth.getCurrentUser().getUid()).setValue(favoriteProducts);
                    callback.onAddFavoriteProduct(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private byte[] convertImageView(ImageView imageView) {
        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        return baos.toByteArray();
    }

//    private UserData getUserDataFromFirebase() {
//        final UserData[] user = new UserData[1];
//        databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(auth.getCurrentUser().getUid());
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String name = dataSnapshot.child("name").getValue(String.class);
//                String email = dataSnapshot.child("email").getValue(String.class);
//                String phoneNumber = dataSnapshot.child("phoneNumber").getValue(String.class);
//                user[0] = new UserData(name,email,phoneNumber,new ArrayList<String>(),new ArrayList<String>());
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//        return user[0];
//    }

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
        String pattern = "^(?=.*[0-9])(?=.*[a-z]" +
                ")(?=.*[A-Z])(?=\\S+$).{8,}$";
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
 /*   private String loadJSONFromAsset() {
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
    }*/
    private boolean loadCatalogFromJSON(String json, List<ProductItem> productItemList) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        try {
            JSONObject jsonObject = new JSONObject(json);
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                JSONArray jsonArray = jsonObject.getJSONArray(key);
                if (jsonArray.length() > 0) {
                    List<ProductItem> productItems = Arrays.asList(gson.fromJson(jsonArray.toString(), ProductItem[].class));
                    productItemList.addAll(productItems);
                    Log.e(TAG, "loadCatalogFromJSON.productItem" + productItemList.get(0).name);
                }

            }
            return true;
        } catch (JSONException error) {
        }
        return false;
    }

    private boolean loadMyProductsFromJSON(String json, String key, List<ProductItem> productItemList) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray(key);
            if (jsonArray.length() > 0) {
                List<ProductItem> productItems = Arrays.asList(gson.fromJson(jsonArray.toString(), ProductItem[].class));
                productItemList.addAll(productItems);
                Log.e(TAG, "loadCatalogFromJSON.productItem" + productItemList.get(0).name);
            }
            return true;
        } catch (JSONException error) {
        }
        return false;
    }


    @Override
    public void getJSONFromURL(final OnGetJSONCallback getJSONCallback, final List<ProductItem> productItemList) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, JSON_FILE, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        loadCatalogFromJSON(response.toString(), productItemList);
                        getJSONCallback.onGetJSON(false);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                getJSONCallback.onGetJSON(true);
            }
        });
        requestQueue.add(request);
    }

    private boolean loadFavoriteProductsFromJSON(String json, String key, List<ProductItem> productFavoriteList) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray(key);
            if (jsonArray.length() > 0) {
                List<ProductItem> productItems = Arrays.asList(gson.fromJson(jsonArray.toString(), ProductItem[].class));
                productFavoriteList.addAll(productItems);
                Log.e(TAG, "loadCatalogFromJSON.productItem" + productFavoriteList.get(0).name);
            }
            return true;
        } catch (JSONException error) {
        }
        return false;
    }
    @Override
    public void getMyProductsJSONFromURL(final OnGetMyProductsJSONCallback getMyProductsJSONCallback, final List<ProductItem> myProductItemList) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, JSON_FILE, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        loadMyProductsFromJSON(response.toString(), auth.getCurrentUser().getUid(), myProductItemList);
                        getMyProductsJSONCallback.onGetJSON(false);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                getMyProductsJSONCallback.onGetJSON(true);
            }
        });
        requestQueue.add(request);
    }

    @Override
    public void getUserProfileData(final UserData userData, final OnGetUserProfileDataCallback getUserProfileData) {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(auth.getCurrentUser().getUid());
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue(String.class);
                String email = dataSnapshot.child("email").getValue(String.class);
                String phoneNumber = dataSnapshot.child("phoneNumber").getValue(String.class);
                //Log.e(TAG,"phoneNumber" + phoneNumber);
                userData.setName(name);
                userData.setEmail(email);
                userData.setPhoneNumber(phoneNumber);
                getUserProfileData.onGetProfileData(false);
            }
    @Override
    public void getFavoriteJSONFromURL(final GetFavoriteJSONCallback getFavoriteJSONCallback, final List<ProductItem> favoriteItemList) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, JSON_FAVORITE, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        loadFavoriteProductsFromJSON(response.toString(), auth.getCurrentUser().getUid(), favoriteItemList);
                        getFavoriteJSONCallback.onGetFavoriteJSONCallback(false);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                getFavoriteJSONCallback.onGetFavoriteJSONCallback(true);
            }
        });
        requestQueue.add(request);
    }
  /*  private class JsonTask extends AsyncTask<String, String, String> {

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                getUserProfileData.onGetProfileData(true);
            }
        });
    }

    /*  private class JsonTask extends AsyncTask<String, String, String> {

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
    }*/
}
