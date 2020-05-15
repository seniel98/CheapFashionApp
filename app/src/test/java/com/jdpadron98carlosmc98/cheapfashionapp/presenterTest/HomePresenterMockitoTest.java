package com.jdpadron98carlosmc98.cheapfashionapp.presenterTest;

import com.jdpadron98carlosmc98.cheapfashionapp.Home.HomeContract;
import com.jdpadron98carlosmc98.cheapfashionapp.Home.HomePresenter;
import com.jdpadron98carlosmc98.cheapfashionapp.Home.HomeState;
import com.jdpadron98carlosmc98.cheapfashionapp.data.ProductItem;
import com.jdpadron98carlosmc98.cheapfashionapp.data.RepositoryContract;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.ref.WeakReference;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class HomePresenterMockitoTest {
    @Captor
    private ArgumentCaptor<RepositoryContract.GetProductListCallback> getProductListCallbackArgumentCaptor;

    @Captor
    private ArgumentCaptor<RepositoryContract.GetFavoriteJSONCallback> getFavoriteJSONCallbackArgumentCaptor;

    @Captor
    private ArgumentCaptor<RepositoryContract.OnGetJSONCallback> onGetJSONCallbackArgumentCaptor;

    @Captor
    private ArgumentCaptor<RepositoryContract.OnLogoutCallback> onLogoutCallbackArgumentCaptor;

    @Mock
    private HomeContract.Model modelMock;

    @Mock
    private HomeContract.Presenter presenterMock;

    @Mock
    private HomeContract.Router routerMock;

    @Mock
    private HomeContract.View viewMock;


    private HomeState homeState;

    private static final List<ProductItem> productList = mock(List.class);

    private static final ProductItem productItem = mock(ProductItem.class);

    private static final String ERROR_STRING = "NO CONNECTION. DATA MAY BE OBSOLETE";

    @Before
    public void setupForgotPasswordScreen() {
        homeState = new HomeState();
        // To inject the mocks in the test this method needs to be called
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        presenterMock = new HomePresenter(homeState);

        // Inject dependencies to the class under test
        presenterMock.injectView(new WeakReference<>(viewMock));
        presenterMock.injectModel(modelMock);
        presenterMock.injectRouter(routerMock);
    }


    @Test
    public void goToDetail(){
        presenterMock.selectProduct(productItem);
        verify(routerMock).passStateToNextScreen(homeState);
        verify(viewMock).goToDetail();
    }

    @Test
    public void logOut(){
        presenterMock.callLogout();
        verify(modelMock).logout(onLogoutCallbackArgumentCaptor.capture());
        onLogoutCallbackArgumentCaptor.getValue().onLogout(false);
        verify(viewMock).goToLogin();
    }

    @Test
    public void goToFavorite(){
        presenterMock.goToFavorites();
        verify(viewMock).goToFavorites();
    }

    @Test
    public void goToProfile(){
        presenterMock.goToProfile();
        verify(viewMock).goToProfile();
    }

    @Test
    public void goToMyProducts(){
        presenterMock.goToMyProducts();
        verify(viewMock).goToMyProducts();
    }

    @Test
    public void loadJSONFavorite(){
        presenterMock.loadFavoriteList();
        verify(modelMock).getFavoriteDataFromRepository(getFavoriteJSONCallbackArgumentCaptor.capture());
        getFavoriteJSONCallbackArgumentCaptor.getValue().onGetFavoriteJSONCallback(false);
    }

    @Test
    public void loadJSONProducts(){
        presenterMock.downloadDataFromRepository();
        verify(modelMock).getDataFromRepository(onGetJSONCallbackArgumentCaptor.capture());
        onGetJSONCallbackArgumentCaptor.getValue().onGetJSON(false);
        verify(modelMock).getProductListData(getProductListCallbackArgumentCaptor.capture());
        getProductListCallbackArgumentCaptor.getValue().setProductList(productList);
        verify(viewMock).fillArrayList(homeState);
    }

    @Test
    public void loadJSONProductsWithoutConnection(){
        presenterMock.downloadDataFromRepository();
        verify(modelMock).getDataFromRepository(onGetJSONCallbackArgumentCaptor.capture());
        onGetJSONCallbackArgumentCaptor.getValue().onGetJSON(true);
        verify(viewMock).showToast(ERROR_STRING);
        verify(modelMock).getProductListData(getProductListCallbackArgumentCaptor.capture());
        getProductListCallbackArgumentCaptor.getValue().setProductList(productList);
        verify(viewMock).fillArrayList(homeState);
    }
}

