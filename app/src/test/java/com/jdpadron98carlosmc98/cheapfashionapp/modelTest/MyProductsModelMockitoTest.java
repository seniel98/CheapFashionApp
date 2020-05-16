package com.jdpadron98carlosmc98.cheapfashionapp.modelTest;


import com.jdpadron98carlosmc98.cheapfashionapp.MyProducts.MyProductsContract;
import com.jdpadron98carlosmc98.cheapfashionapp.MyProducts.MyProductsModel;
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

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MyProductsModelMockitoTest {

    @Captor
    private ArgumentCaptor<RepositoryContract.OnLogoutCallback> onLogoutCallbackArgumentCaptor;

    @Captor
    private ArgumentCaptor<RepositoryContract.OnGetMyProductsCallback> onGetMyProductsCallbackArgumentCaptor;

    @Captor
    private ArgumentCaptor<RepositoryContract.DeleteProductCallback> deleteProductCallbackArgumentCaptor;

    @Mock
    private RepositoryContract repository;

    private MyProductsContract.Model model;

    private RepositoryContract.OnLogoutCallback logoutCallbackMock = mock(RepositoryContract.OnLogoutCallback.class);

    private RepositoryContract.OnGetMyProductsCallback onGetMyProductsCallbackMock = mock(RepositoryContract.OnGetMyProductsCallback.class);

    private RepositoryContract.DeleteProductCallback deleteProductCallbackMock = mock(RepositoryContract.DeleteProductCallback.class);

    private ProductItem productItemMock = mock(ProductItem.class);

    @Before
    public void setup() {
        // To inject the mocks in the test this method needs to be called
        MockitoAnnotations.initMocks(this);

        model = new MyProductsModel(repository);
    }

    @Test
    public void verifyLogout() {
        model.logout(logoutCallbackMock);
        verify(repository).logout(onLogoutCallbackArgumentCaptor.capture());
    }

    @Test
    public void verifyGetDataFromRepository() {
        model.getDataFromRepository(onGetMyProductsCallbackMock);
        verify(repository).getMyProductsFromDatabase(onGetMyProductsCallbackArgumentCaptor.capture());
    }

    @Test
    public void verifyDeleteProduct() {
        model.deleteProduct(productItemMock, deleteProductCallbackMock);
        verify(repository).deleteProduct(eq(productItemMock), deleteProductCallbackArgumentCaptor.capture());
    }

}
