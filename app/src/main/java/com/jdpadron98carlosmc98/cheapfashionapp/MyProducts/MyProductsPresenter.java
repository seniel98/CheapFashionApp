package com.jdpadron98carlosmc98.cheapfashionapp.MyProducts;

import com.jdpadron98carlosmc98.cheapfashionapp.data.ProductItem;
import com.jdpadron98carlosmc98.cheapfashionapp.data.RepositoryContract;

import java.lang.ref.WeakReference;
import java.util.List;

public class MyProductsPresenter implements MyProductsContract.Presenter {

    public static String TAG = MyProductsPresenter.class.getSimpleName();

    private WeakReference<MyProductsContract.View> view;
    private MyProductsState state;
    private MyProductsContract.Model model;
    private MyProductsContract.Router router;

    public MyProductsPresenter(MyProductsState state) {
        this.state = state;
    }

    @Override
    public void onStart() {
        // Log.e(TAG, "onStart()");

        // initialize the state if is necessary
        if (state == null) {
            state = new MyProductsState();
        }

        // use passed state if is necessary
        MyProductsState savedState = router.getStateFromPreviousScreen();
        if (savedState != null) {

            // update the model if is necessary
            //model.onDataFromPreviousScreen(savedState.data);
        }
        //Correccion para guardar el estado de la lista en el caso de cargar los items para la prueba
  /*      List<ProductItem> list = getListFromModel();
        state.list = list;
        view.get().fillArrayList(state);*/
    }

    @Override
    public void onRestart() {
        // Log.e(TAG, "onRestart()");
        if (state == null) {
            state = new MyProductsState();
        }

        // use passed state if is necessary
        MyProductsState savedState = router.getStateFromPreviousScreen();
        if (savedState != null) {

            // update the model if is necessary
            //model.onDataFromPreviousScreen(savedState.data);
        }
        //Correccion para guardar el estado de la lista en el caso de cargar los items para la prueba
       /* List<ProductItem> list = getListFromModel();
        state.list = list;
        view.get().fillArrayList(state);*/
        // update the model if is necessary
        //model.onRestartScreen(state.data);
    }

    @Override
    public void onResume() {
        // Log.e(TAG, "onResume()");

        // use passed state if is necessary
        MyProductsState savedState = router.getStateFromNextScreen();
        if (savedState != null) {

            // update the model if is necessary
            // model.onDataFromNextScreen(savedState.data);
        }

        // call the model and update the state
        //state.data = model.getStoredData();

        // update the view
        view.get().onDataUpdated(state);

    }

    @Override
    public void onBackPressed() {
        // Log.e(TAG, "onBackPressed()");
        view.get().goToHome();
    }

    @Override
    public void onPause() {
        // Log.e(TAG, "onPause()");
    }

    @Override
    public void onDestroy() {
        // Log.e(TAG, "onDestroy()");
    }

    @Override
    public void selectProduct(ProductItem item) {

    }

    @Override
    public void goToFavorites() {
        view.get().goToFavorites();
    }

    @Override
    public void goToHome() {
        view.get().goToHome();
    }

    @Override
    public void goToProfile() {
        view.get().goToProfile();
    }

    @Override
    public void callLogout() {
        model.logout(new RepositoryContract.OnLogoutCallback() {
            @Override
            public void onLogout(boolean error) {
                if (!error) {
                    view.get().showToast("Logged out successfully!");
                    view.get().goToLogin();
                } else {

                }
            }
        });
    }

    @Override
    public void getDataFromRepository() {
        model.getDataFromRepository(new RepositoryContract.OnGetMyProductsCallback() {
            @Override
            public void setProductList(List<ProductItem> loadProducts) {
                state.myProductsList = loadProducts;
                view.get().fillArrayList(state);
            }
        });
    }

    @Override
    public void deleteProduct(ProductItem item) {
        model.deleteProduct(item, new RepositoryContract.DeleteProductCallback() {
            @Override
            public void onDelete(boolean error, List<ProductItem> productItems) {
                if (!error) {
                    state.myProductsList = productItems;
                    view.get().fillArrayList(state);
                }
            }
        });
    }

    @Override
    public void goToAddProduct() {
        view.get().goAddProduct();
    }

    @Override
    public void injectView(WeakReference<MyProductsContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(MyProductsContract.Model model) {
        this.model = model;
    }

    @Override
    public void injectRouter(MyProductsContract.Router router) {
        this.router = router;
    }
}
