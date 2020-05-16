package com.jdpadron98carlosmc98.cheapfashionapp.presenterTest;

import android.widget.ImageView;

import com.jdpadron98carlosmc98.cheapfashionapp.AddProduct.AddProductContract;
import com.jdpadron98carlosmc98.cheapfashionapp.AddProduct.AddProductPresenter;
import com.jdpadron98carlosmc98.cheapfashionapp.AddProduct.AddProductState;
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
public class AddProductPresenterMockitoTest {
    @Captor
    private ArgumentCaptor<RepositoryContract.CreateProductEntryCallBack> createProductEntryCallBackArgumentCaptor;

    @Mock
    private AddProductContract.Model modelMock;

    @Mock
    private AddProductContract.Presenter presenterMock;

    @Mock
    private AddProductContract.Router routerMock;

    @Mock
    private AddProductContract.View viewMock;


    private AddProductState addProductState;

    private static final String productName = "name";

    private static final String productPrice = "price";

    private static final String productDescription = "description";

    private static final ImageView imageView = mock(ImageView.class);

    private static final String MESSAGE = "Product added";

    @Before
    public void setupForgotPasswordScreen() {
        addProductState = new AddProductState();
        // To inject the mocks in the test this method needs to be called
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        presenterMock = new AddProductPresenter(addProductState);

        // Inject dependencies to the class under test
        presenterMock.injectView(new WeakReference<>(viewMock));
        presenterMock.injectModel(modelMock);
        presenterMock.injectRouter(routerMock);
    }

    @Test
    public void addNewProductSuccessfully(){
        presenterMock.addNewProduct(productName,productPrice,productDescription,imageView);
        verify(modelMock).addProduct(eq(productName),eq(productPrice),eq(productDescription),
                eq(imageView),createProductEntryCallBackArgumentCaptor.capture());
        createProductEntryCallBackArgumentCaptor.getValue().onAddNewProduct(false);
        addProductState.message = "Product added";
        verify(viewMock).displayData(addProductState);
        verify(viewMock).goHome();


    }

}
