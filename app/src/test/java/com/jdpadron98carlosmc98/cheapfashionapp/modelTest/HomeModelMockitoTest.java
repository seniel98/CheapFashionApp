package com.jdpadron98carlosmc98.cheapfashionapp.modelTest;

import com.jdpadron98carlosmc98.cheapfashionapp.Home.HomeContract;
import com.jdpadron98carlosmc98.cheapfashionapp.Home.HomeModel;
import com.jdpadron98carlosmc98.cheapfashionapp.data.RepositoryContract;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class HomeModelMockitoTest {

    @Captor
    private ArgumentCaptor<RepositoryContract.GetProductListCallback> getProductListCallbackArgumentCaptor;

    @Captor
    private ArgumentCaptor<RepositoryContract.OnGetJSONCallback> onGetJSONCallbackArgumentCaptor;

    @Captor
    private ArgumentCaptor<RepositoryContract.GetFavoriteJSONCallback> getFavoriteJSONCallbackArgumentCaptor;

    @Captor
    private ArgumentCaptor<RepositoryContract.OnLogoutCallback> logoutCallbackArgumentCaptor;

    @Mock
    private RepositoryContract repositoryMock;

    private HomeContract.Model model;

    private RepositoryContract.GetProductListCallback productListCallbackMock = mock(RepositoryContract.GetProductListCallback.class);

    private RepositoryContract.OnGetJSONCallback onGetJSONCallbackMock = mock(RepositoryContract.OnGetJSONCallback.class);

    private RepositoryContract.GetFavoriteJSONCallback getFavoriteJSONCallbackMock = mock(RepositoryContract.GetFavoriteJSONCallback.class);

    private RepositoryContract.OnLogoutCallback logoutCallbackMock = mock(RepositoryContract.OnLogoutCallback.class);


    @Before
    public void setup() {
        // To inject the mocks in the test this method needs to be called
        MockitoAnnotations.initMocks(this);


        model = new HomeModel(repositoryMock);
    }


    @Test
    public void verifyGetProductListData() {
        model.getProductListData(productListCallbackMock);
        verify(repositoryMock).getProductList(getProductListCallbackArgumentCaptor.capture());
    }

    @Test
    public void verifyGetDataFromRepository() {
        model.getDataFromRepository(onGetJSONCallbackMock);
        verify(repositoryMock).getJSONFromURL(onGetJSONCallbackArgumentCaptor.capture());
    }

    @Test
    public void verifyGetFavoriteDataFromRepository() {
        model.getFavoriteDataFromRepository(getFavoriteJSONCallbackMock);
        verify(repositoryMock).getFavoriteJSONFromURL(getFavoriteJSONCallbackArgumentCaptor.capture());
    }

    @Test
    public void verifyLogout() {
        model.logout(logoutCallbackMock);
        verify(repositoryMock).logout(logoutCallbackArgumentCaptor.capture());
    }
}
