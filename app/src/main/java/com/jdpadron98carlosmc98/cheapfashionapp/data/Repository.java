package com.jdpadron98carlosmc98.cheapfashionapp.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.room.Room;

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
import com.jdpadron98carlosmc98.cheapfashionapp.database.CatalogDatabase;
import com.jdpadron98carlosmc98.cheapfashionapp.database.FavoriteDao;
import com.jdpadron98carlosmc98.cheapfashionapp.database.ProductDao;

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
    public static final String DB_FILE = "catalog.db";

    private static String passwordNotValidMessage = "Password must be at least 8 characters long with alphanumeric format";
    private static String registeredOkMessage = "Registered successfully";


    private CatalogDatabase roomDatabase;
    private FirebaseAuth auth;
    private StorageReference storageRef;
    private DatabaseReference usersRef;
    private DatabaseReference productsRef;
    private DatabaseReference favoriteRef;
    private DatabaseReference databaseReference;
    // private String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.";


    //private List<ProductItem> productItemList = new ArrayList<>();


    public static RepositoryContract getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new Repository(context);
        }
        return INSTANCE;
    }


    private Repository(Context context) {
        this.context = context;

        roomDatabase = Room.databaseBuilder(context, CatalogDatabase.class, DB_FILE).build();

        auth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        storageRef = FirebaseStorage.getInstance().getReference();

        usersRef = FirebaseDatabase.getInstance().getReference().child("users");

        productsRef = FirebaseDatabase.getInstance().getReference().child("products");

        favoriteRef = FirebaseDatabase.getInstance().getReference().child("favoriteProducts");


        clearDBTable();

    }

    private void clearDBTable() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                roomDatabase.clearAllTables();
            }
        });
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
        if (productDescription.equals("") ||
                productPrice.equals("") ||
                productName.equals("") ||
                imageView.getDrawable() == null) {
            callback.onAddNewProduct(true);
        } else {
            //final UserData user = getUserDataFromFirebase();
            final UserData[] user = new UserData[1];
            //databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(auth.getCurrentUser().getUid());
            databaseReference = FirebaseDatabase.getInstance().getReference();
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
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
                        final String pid = UUID.randomUUID().toString().replace("-", "");
                        ProductItem productItem = new ProductItem(pid, productPrice, productName, url, productDescription, auth.getCurrentUser().getUid(), user[0]);
                        productsRef.child(auth.getCurrentUser().getUid()).child(pid).setValue(productItem);
                        callback.onAddNewProduct(false);
                    }
                }
            });
        }
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
        final List<FavoriteItem> favoriteProducts = new ArrayList<>();
        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Pillamos un tipo generico segun las recomendaciones de Firebase, ver https://firebase.googleblog.com/2014/04/best-practices-arrays-in-firebase.html
                GenericTypeIndicator<List<FavoriteItem>> genericTypeIndicator = new GenericTypeIndicator<List<FavoriteItem>>() {
                };
                List<FavoriteItem> productList = dataSnapshot.child(auth.getCurrentUser().getUid()).child("favorite").getValue(genericTypeIndicator);
                //Log.e(TAG,"productList"+ dataSnapshot.child("products").child(auth.getCurrentUser().getUid()).getValue(genericTypeIndicator));

                if (productList != null) {
                    favoriteProducts.addAll(productList);
                }
                UUID uuid = UUID.randomUUID();
                String randomUUIDString = uuid.toString();
                FavoriteItem favoriteItem = new FavoriteItem(randomUUIDString, productItem.uid, productItem.getPid());
                if (favoriteProducts.size() == 0) {
                    favoriteProducts.add(favoriteItem);
                    usersRef.child(auth.getCurrentUser().getUid()).child("favorite").setValue(favoriteProducts);
                    callback.onAddFavoriteProduct(false);
                } else {
                    for (FavoriteItem product : favoriteProducts) {
                        if (product.getPid().equals(favoriteItem.getPid())) {
                            deleteFavoriteProduct(favoriteItem);
                            callback.onAddFavoriteProduct(true);
                            return;
                        }
                    }
                    favoriteProducts.add(favoriteItem);
                    usersRef.child(auth.getCurrentUser().getUid()).child("favorite").setValue(favoriteProducts);
                    callback.onAddFavoriteProduct(false);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void deleteFavoriteProduct(final FavoriteItem favoriteItem) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                List<FavoriteItem> favoritePIDList = new ArrayList<>();
                favoritePIDList.addAll(getFavoriteDao().loadFavoriteProducts());

                for (FavoriteItem product : favoritePIDList) {
                    if (product.getPid().equals(favoriteItem.getPid())) {
                        favoritePIDList.remove(product);
                        getFavoriteDao().deleteFavoriteProduct(product);
                        break;
                    }
                }
                usersRef.child(auth.getCurrentUser().getUid()).child("favorite").setValue(favoritePIDList);
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

    private boolean loadCatalogFromJSON(String json) {
        try {
            List<ProductItem> productItems = new ArrayList<>();
            JSONObject jsonObject = new JSONObject(json);
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                JSONObject jsonObjectProductList = jsonObject.getJSONObject(key);
                Iterator<String> productKeys = jsonObjectProductList.keys();
                while (productKeys.hasNext()) {

                    String productKey = productKeys.next();
                    JSONObject jsonObjectProductData = jsonObjectProductList.getJSONObject(productKey);
                    UserData userData = new UserData("", "", "");
                    ProductItem productItem = new ProductItem("", "", "", "", "", "", userData);

                    productItem.detail = jsonObjectProductData.getString("detail");
                    productItem.name = jsonObjectProductData.getString("name");
                    productItem.picture = jsonObjectProductData.getString("picture");
                    productItem.pid = jsonObjectProductData.getString("pid");
                    productItem.price = jsonObjectProductData.getString("price");
                    productItem.uid = jsonObjectProductData.getString("uid");

                    userData.setEmail(jsonObjectProductData.getJSONObject("userData").getString("email"));
                    userData.setName(jsonObjectProductData.getJSONObject("userData").getString("name"));
                    userData.setPhoneNumber(jsonObjectProductData.getJSONObject("userData").getString("phoneNumber"));

                    productItem.userData = userData;

                    productItems.add(productItem);
                }
            }
            insertListInDB(productItems);
            return true;
        } catch (JSONException error) {
        }
        return false;
    }


    /**
     * Metodo para insertar los productos en la base de datos en un hilo secundario
     *
     * @param productItems
     */
    private void insertListInDB(final List<ProductItem> productItems) {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                for (ProductItem product : productItems) {
                    getProductDao().insertProduct(product);
                }
            }
        });
    }

    @Override
    public void getProductList(final GetProductListCallback callback) {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.setProductList(getProductDao().loadProducts());
                }
            }
        });

    }

    @Override
    public void getFavoriteList(final GetFavoriteListCallback callback) {
        final List<ProductItem> favoriteList = new ArrayList<>();
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    final List<FavoriteItem> favoritePIDList = new ArrayList<>();
                    favoritePIDList.addAll(getFavoriteDao().loadFavoriteProducts());
                    Log.e(TAG, "getProductListData.Repository" + favoritePIDList.size());
                    //Aqui comprobamos si el elemento de favoritos se encuentra en la BDD en firebase
                    productsRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (favoritePIDList.size() == 0) {
                                callback.setFavoriteList(favoriteList);
                            } else {
                                for (final FavoriteItem pid : favoritePIDList) {
                                    if (!dataSnapshot.child(pid.getUid()).hasChild(pid.getPid())) {
                                        //Si no esta en la base de datos lo borramos de la local
                                        deleteFavoriteProduct(pid);
                                    } else {
                                        //Ejecutamos el metodo de cargar los favortitos en otro hilo distinto del principal
                                        AsyncTask.execute(new Runnable() {
                                            @Override
                                            public void run() {
                                                ProductItem productItem = getProductDao().loadFavoriteProducts(pid.getPid());
                                                favoriteList.add(productItem);
                                                callback.setFavoriteList(favoriteList);
                                            }
                                        });
                                    }
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });

    }


    @Override
    public void getJSONFromURL(final OnGetJSONCallback getJSONCallback) {
        final RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, JSON_FILE, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        loadCatalogFromJSON(response.toString());
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

    private boolean loadFavoriteProductsFromJSON(String json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("favorite");
            if (jsonArray.length() > 0) {
                List<FavoriteItem> favoriteItems = Arrays.asList(gson.fromJson(jsonArray.toString(), FavoriteItem[].class));
                insertFavoriteListInDB(favoriteItems);
            }
            return true;
        } catch (JSONException error) {
        }
        return false;
    }

    private void insertFavoriteListInDB(final List<FavoriteItem> favoriteItems) {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                for (FavoriteItem product : favoriteItems) {
                    getFavoriteDao().insertFavoriteProduct(product);
                }
            }
        });
    }

    @Override
    public void getMyProductsFromDatabase(final OnGetMyProductsCallback getMyProductsCallback) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                if (getMyProductsCallback != null) {
                    getMyProductsCallback.setProductList(getProductDao().loadMyProducts(auth.getCurrentUser().getUid()));
                }
            }
        });
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
            public void onCancelled(@NonNull DatabaseError databaseError) {
                getUserProfileData.onGetProfileData(true);
            }
        });
    }

    @Override
    public void checkIfIsFavorite(final String productID, final IsFavoriteCallback isFavoriteCallback) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                if (isFavoriteCallback != null) {
                    List<FavoriteItem> favoritePIDList = new ArrayList<>();
                    favoritePIDList.addAll(getFavoriteDao().loadFavoriteProducts());
                    for (FavoriteItem pid : favoritePIDList) {
                        if (pid.getPid().equals(productID)) {
                            isFavoriteCallback.isFavorite(true);
                            return;
                        }
                    }
                    isFavoriteCallback.isFavorite(false);
                }

            }
        });
    }

    @Override
    public void deleteProduct(final ProductItem item, final DeleteProductCallback deleteProductCallback) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                List<ProductItem> productItems = new ArrayList<>();
                productItems.addAll(getProductDao().loadMyProducts(auth.getCurrentUser().getUid()));

                for (ProductItem product : productItems) {
                    if (product.getPid().equals(item.getPid())) {
                        productItems.remove(product);
                        getProductDao().deleteProduct(product);
                        break;
                    }
                }
                productsRef.child(auth.getCurrentUser().getUid()).setValue(productItems);
                deleteProductCallback.onDelete(false, productItems);
            }
        });
    }


    @Override
    public void getFavoriteJSONFromURL(final GetFavoriteJSONCallback getFavoriteJSONCallback) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String JSON_FILE = "https://cheap-fashion-app.firebaseio.com/users/" + auth.getCurrentUser().getUid() + "/.json";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, JSON_FILE, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        loadFavoriteProductsFromJSON(response.toString());
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


    private ProductDao getProductDao() {
        return roomDatabase.productDao();
    }

    private FavoriteDao getFavoriteDao() {
        return roomDatabase.favoriteDao();
    }
}
