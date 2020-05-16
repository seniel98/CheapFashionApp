package com.jdpadron98carlosmc98.cheapfashionapp.modelTest;

import com.jdpadron98carlosmc98.cheapfashionapp.ProductDetail.ProductDetailContract;
import com.jdpadron98carlosmc98.cheapfashionapp.ProductDetail.ProductDetailModel;
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
public class ProductDetailModelMockitoTest {

    @Captor
    private ArgumentCaptor<RepositoryContract.IsFavoriteCallback> isFavoriteCallbackArgumentCaptor;

    @Captor
    private ArgumentCaptor<RepositoryContract.CreateFavoriteProductEntryCallBack> createFavoriteProductEntryCallBackArgumentCaptor;

    @Mock
    private RepositoryContract repositoryMock;

    private RepositoryContract.IsFavoriteCallback isFavoriteCallbackMock = mock(RepositoryContract.IsFavoriteCallback.class);

    private RepositoryContract.CreateFavoriteProductEntryCallBack createFavoriteProductEntryCallBackMock = mock(RepositoryContract.CreateFavoriteProductEntryCallBack.class);

    private ProductDetailContract.Model model;

    private ProductItem productItem = mock(ProductItem.class);

    private final String PID = "pid";


    @Before
    public void setupSignUpScreen() {
        // To inject the mocks in the test this method needs to be called
        MockitoAnnotations.initMocks(this);

        model = new ProductDetailModel(repositoryMock);

    }


    @Test
    public void verifyCheckIfProductFavorite() {
        model.checkIfProductFavorite(PID, isFavoriteCallbackMock);
        verify(repositoryMock).checkIfIsFavorite(eq(PID), isFavoriteCallbackArgumentCaptor.capture());
    }

    @Test
    public void verifyAddProductToFavorite() {
        model.addProductToFavorite(productItem, createFavoriteProductEntryCallBackMock);
        verify(repositoryMock).addFavoriteProduct(eq(productItem), createFavoriteProductEntryCallBackArgumentCaptor.capture());
    }

}
