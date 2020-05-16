package com.jdpadron98carlosmc98.cheapfashionapp.modelTest;

import com.jdpadron98carlosmc98.cheapfashionapp.Favorite.FavoriteContract;
import com.jdpadron98carlosmc98.cheapfashionapp.Favorite.FavoriteModel;
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
public class FavoriteModelMockitoTest {

    @Captor
    private ArgumentCaptor<RepositoryContract.GetFavoriteJSONCallback> getFavoriteJSONCallbackArgumentCaptor;

    @Captor
    private ArgumentCaptor<RepositoryContract.GetFavoriteListCallback> getFavoriteListCallbackArgumentCaptor;

    @Captor
    private ArgumentCaptor<RepositoryContract.OnLogoutCallback> onLogoutCallbackArgumentCaptor;

    @Mock
    private RepositoryContract repository;

    private FavoriteContract.Model model;

    private RepositoryContract.GetFavoriteJSONCallback getFavoriteJSONCallbackMock = mock(RepositoryContract.GetFavoriteJSONCallback.class);

    private RepositoryContract.GetFavoriteListCallback getFavoriteListCallbackMock = mock(RepositoryContract.GetFavoriteListCallback.class);

    private RepositoryContract.OnLogoutCallback onLogoutCallbackMock = mock(RepositoryContract.OnLogoutCallback.class);

    @Before
    public void setup() {
        // To inject the mocks in the test this method needs to be called
        MockitoAnnotations.initMocks(this);

        model = new FavoriteModel(repository);
    }

    @Test
    public void verifyGetDataFromRepository() {
        model.getDataFromRepository(getFavoriteJSONCallbackMock);
        verify(repository).getFavoriteJSONFromURL(getFavoriteJSONCallbackArgumentCaptor.capture());
    }

    @Test
    public void verifyGetFavoriteListData() {
        model.getFavoriteListData(getFavoriteListCallbackMock);
        verify(repository).getFavoriteList(getFavoriteListCallbackArgumentCaptor.capture());
    }

    @Test
    public void verifyLogout() {
        model.logout(onLogoutCallbackMock);
        verify(repository).logout(onLogoutCallbackArgumentCaptor.capture());

    }


}
