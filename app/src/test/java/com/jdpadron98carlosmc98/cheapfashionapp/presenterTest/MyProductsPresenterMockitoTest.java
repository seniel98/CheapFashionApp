package com.jdpadron98carlosmc98.cheapfashionapp.presenterTest;

import com.jdpadron98carlosmc98.cheapfashionapp.MyProducts.MyProductsContract;
import com.jdpadron98carlosmc98.cheapfashionapp.MyProducts.MyProductsPresenter;
import com.jdpadron98carlosmc98.cheapfashionapp.MyProducts.MyProductsState;
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

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MyProductsPresenterMockitoTest {
    @Captor
    private ArgumentCaptor<RepositoryContract.OnLogoutCallback> onLogoutCallbackArgumentCaptor;

    @Captor
    private ArgumentCaptor<RepositoryContract.DeleteProductCallback> deleteProductCallbackArgumentCaptor;

    @Captor
    private ArgumentCaptor<RepositoryContract.OnGetMyProductsCallback> myProductsCallbackArgumentCaptor;

    @Mock
    private MyProductsContract.Model modelMock;

    @Mock
    private MyProductsContract.Presenter presenterMock;

    @Mock
    private MyProductsContract.Router routerMock;

    @Mock
    private MyProductsContract.View viewMock;

    private MyProductsState myProductsState;

    private static final ProductItem product = mock(ProductItem.class);

    private static final List<ProductItem> productList = mock(List.class);


    @Before
    public void setupForgotPasswordScreen(){
        myProductsState = new MyProductsState();
        // To inject the mocks in the test this method needs to be called
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        presenterMock = new MyProductsPresenter(myProductsState);

        // Inject dependencies to the class under test
        presenterMock.injectView(new WeakReference<>(viewMock));
        presenterMock.injectModel(modelMock);
        presenterMock.injectRouter(routerMock);
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
    public void goToHome(){
        presenterMock.goToHome();
        verify(viewMock).goToHome();
    }

    @Test
    public void deleteProduct(){
        presenterMock.deleteProduct(product);
        verify(modelMock).deleteProduct(eq(product),deleteProductCallbackArgumentCaptor.capture());
        deleteProductCallbackArgumentCaptor.getValue().onDelete(false,productList);
        myProductsState.myProductsList = productList;
        verify(viewMock).fillArrayList(myProductsState);
    }

    @Test
    public void getData() {
        presenterMock.getDataFromRepository();
        verify(modelMock).getDataFromRepository(myProductsCallbackArgumentCaptor.capture());
        myProductsCallbackArgumentCaptor.getValue().setProductList(productList);
        myProductsState.myProductsList = productList;
        verify(viewMock).fillArrayList(myProductsState);
    }
}
