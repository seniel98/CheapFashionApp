package com.jdpadron98carlosmc98.cheapfashionapp.modelTest;

import com.jdpadron98carlosmc98.cheapfashionapp.Profile.ProfileContract;
import com.jdpadron98carlosmc98.cheapfashionapp.Profile.ProfileModel;
import com.jdpadron98carlosmc98.cheapfashionapp.data.RepositoryContract;
import com.jdpadron98carlosmc98.cheapfashionapp.data.UserData;

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
public class ProfileModelMockitoTest {

    @Captor
    private ArgumentCaptor<RepositoryContract.OnLogoutCallback> logoutCallbackArgumentCaptor;

    @Captor
    private ArgumentCaptor<RepositoryContract.OnGetUserProfileDataCallback> onGetUserProfileDataCallbackArgumentCaptor;

    @Captor
    private ArgumentCaptor<RepositoryContract.ChangeUserDataCallback> changeUserDataCallbackArgumentCaptor;

    @Mock
    private RepositoryContract repository;

    private ProfileContract.Model model;

    private RepositoryContract.OnLogoutCallback onLogoutCallbackMock = mock(RepositoryContract.OnLogoutCallback.class);

    private RepositoryContract.OnGetUserProfileDataCallback onGetUserProfileDataCallbackMock = mock(RepositoryContract.OnGetUserProfileDataCallback.class);

    private RepositoryContract.ChangeUserDataCallback changeUserDataCallbackMock = mock(RepositoryContract.ChangeUserDataCallback.class);

    private UserData userDataMock = mock(UserData.class);

    private static final String name = "DummyName";

    private static final String phone = "DummyPhone";

    @Before
    public void setUp() {
        // To inject the mocks in the test this method needs to be called
        MockitoAnnotations.initMocks(this);

        model = new ProfileModel(repository);
    }

    @Test
    public void verifyLogout() {
        model.logout(onLogoutCallbackMock);
        verify(repository).logout(logoutCallbackArgumentCaptor.capture());
    }

    @Test
    public void verifyGetUserProfileData() {
        model.getUserProfileData(userDataMock, onGetUserProfileDataCallbackMock);
        verify(repository).getUserProfileData(eq(userDataMock), onGetUserProfileDataCallbackArgumentCaptor.capture());
    }

    @Test
    public void verifyChangeUserData() {
        model.changeUserData(name, phone, changeUserDataCallbackMock);
        verify(repository).changeUserData(eq(name), eq(phone), changeUserDataCallbackArgumentCaptor.capture());

    }

}
