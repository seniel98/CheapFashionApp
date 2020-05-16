package com.jdpadron98carlosmc98.cheapfashionapp.presenterTest;

import com.jdpadron98carlosmc98.cheapfashionapp.ChangePassword.ChangePasswordPresenter;
import com.jdpadron98carlosmc98.cheapfashionapp.ChangePassword.ChangePasswordState;
import com.jdpadron98carlosmc98.cheapfashionapp.ProductDetail.ProductDetailContract;
import com.jdpadron98carlosmc98.cheapfashionapp.ProductDetail.ProductDetailPresenter;
import com.jdpadron98carlosmc98.cheapfashionapp.ProductDetail.ProductDetailState;
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

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductDetailPresenterMockitoTest {
    @Captor
    private ArgumentCaptor<RepositoryContract.IsFavoriteCallback> isFavoriteCallbackArgumentCaptor;

    @Captor
    private ArgumentCaptor<RepositoryContract.CreateFavoriteProductEntryCallBack> createFavoriteProductEntryCallBackArgumentCaptor;

    @Mock
    private ProductDetailContract.Model modelMock;

    @Mock
    private ProductDetailContract.Presenter presenterMock;

    @Mock
    private ProductDetailContract.Router routerMock;

    @Mock
    private ProductDetailContract.View viewMock;

    private ProductDetailState productDetailState;

    private static final ProductItem product = mock(ProductItem.class);

    @Before
    public void setupForgotPasswordScreen(){
        productDetailState = new ProductDetailState();
        // To inject the mocks in the test this method needs to be called
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        presenterMock = new ProductDetailPresenter(productDetailState);

        // Inject dependencies to the class under test
        presenterMock.injectView(new WeakReference<>(viewMock));
        presenterMock.injectModel(modelMock);
        presenterMock.injectRouter(routerMock);
    }

    @Test
    public void checkFavoriteTrue(){
        productDetailState.item = product;
        presenterMock.checkFavorite();
        verify(modelMock).checkIfProductFavorite(eq(productDetailState.item.pid),isFavoriteCallbackArgumentCaptor.capture());
        isFavoriteCallbackArgumentCaptor.getValue().isFavorite(true);
        verify(viewMock).setLikeButtonEnabled();
    }
    @Test
    public void checkFavoriteFalse(){
        productDetailState.item = product;
        presenterMock.checkFavorite();
        verify(modelMock).checkIfProductFavorite(eq(productDetailState.item.pid),isFavoriteCallbackArgumentCaptor.capture());
        isFavoriteCallbackArgumentCaptor.getValue().isFavorite(false);
        verify(viewMock).setLikedButtonDisabled();
    }

    @Test
    public void likePressedNewFavorite(){
        productDetailState.item = product;
        presenterMock.likeButtonPressed();
        verify(modelMock).addProductToFavorite(eq(product),createFavoriteProductEntryCallBackArgumentCaptor.capture());
        createFavoriteProductEntryCallBackArgumentCaptor.getValue().onAddFavoriteProduct(false);
        verify(viewMock).setLikeButtonEnabled();
    }
}
