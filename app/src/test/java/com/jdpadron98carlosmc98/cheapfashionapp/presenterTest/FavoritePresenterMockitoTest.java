package com.jdpadron98carlosmc98.cheapfashionapp.presenterTest;

import android.widget.ImageView;

import com.jdpadron98carlosmc98.cheapfashionapp.Favorite.FavoriteContract;
import com.jdpadron98carlosmc98.cheapfashionapp.Favorite.FavoritePresenter;
import com.jdpadron98carlosmc98.cheapfashionapp.Favorite.FavoriteState;
import com.jdpadron98carlosmc98.cheapfashionapp.data.ProductItem;
import com.jdpadron98carlosmc98.cheapfashionapp.data.RepositoryContract;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.ref.WeakReference;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class FavoritePresenterMockitoTest {
    @Captor
    private ArgumentCaptor<RepositoryContract.OnLogoutCallback> onLogoutCallbackArgumentCaptor;

    @Captor
    private ArgumentCaptor<RepositoryContract.GetFavoriteListCallback> getFavoriteListCallbackArgumentCaptor;

    @Captor
    private ArgumentCaptor<RepositoryContract.GetFavoriteJSONCallback> getFavoriteJSONCallbackArgumentCaptor;

    @Mock
    private FavoriteContract.Model modelMock;

    @Mock
    private FavoriteContract.Presenter presenterMock;

    @Mock
    private FavoriteContract.Router routerMock;

    @Mock
    private FavoriteContract.View viewMock;


    private FavoriteState favoriteState;

    private static final List<ProductItem> itemList = mock(List.class);

    @Before
    public void setupForgotPasswordScreen() {
        favoriteState = new FavoriteState();
        // To inject the mocks in the test this method needs to be called
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        presenterMock = new FavoritePresenter(favoriteState);

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
    public void goToMyProducts(){
        presenterMock.goToMyProducts();
        verify(viewMock).goToMyProducts();
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
    public void getJSONFavoriteItemsWithNoError(){
        presenterMock.downloadDataFromRepository();
        verify(modelMock).getDataFromRepository(getFavoriteJSONCallbackArgumentCaptor.capture());
        getFavoriteJSONCallbackArgumentCaptor.getValue().onGetFavoriteJSONCallback(false);
        verify(modelMock).getFavoriteListData(getFavoriteListCallbackArgumentCaptor.capture());
        getFavoriteListCallbackArgumentCaptor.getValue().setFavoriteList(itemList);
        favoriteState.favoriteItems = itemList;
        verify(viewMock).fillArrayList(favoriteState);
    }
}
