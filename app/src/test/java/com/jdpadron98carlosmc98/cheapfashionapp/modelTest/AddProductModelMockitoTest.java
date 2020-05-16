package com.jdpadron98carlosmc98.cheapfashionapp.modelTest;

import android.widget.ImageView;

import com.jdpadron98carlosmc98.cheapfashionapp.AddProduct.AddProductContract;
import com.jdpadron98carlosmc98.cheapfashionapp.AddProduct.AddProductModel;
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
public class AddProductModelMockitoTest {

    @Captor
    private ArgumentCaptor<RepositoryContract.CreateProductEntryCallBack> createProductEntryCallBackArgumentCaptor;

    @Mock
    private RepositoryContract repository;

    private AddProductContract.Model model;

    private RepositoryContract.CreateProductEntryCallBack createProductEntryCallBackMock = mock(RepositoryContract.CreateProductEntryCallBack.class);

    private final static String productName = "Product 1";

    private final static String productPrice = "10";

    private final static String productDescription = "Product description";

    private ImageView imageViewMock = mock(ImageView.class);

    @Before
    public void setup() {
        // To inject the mocks in the test this method needs to be called
        MockitoAnnotations.initMocks(this);

        model = new AddProductModel(repository);
    }


    @Test
    public void verifyAddProduct() {
        model.addProduct(productName, productPrice, productDescription, imageViewMock, createProductEntryCallBackMock);
        verify(repository).addNewProduct(eq(productName), eq(productPrice), eq(productDescription), eq(imageViewMock), createProductEntryCallBackArgumentCaptor.capture());
    }

}
