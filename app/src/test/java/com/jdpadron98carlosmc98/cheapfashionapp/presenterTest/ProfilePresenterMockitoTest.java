package com.jdpadron98carlosmc98.cheapfashionapp.presenterTest;

import com.jdpadron98carlosmc98.cheapfashionapp.Profile.ProfileContract;
import com.jdpadron98carlosmc98.cheapfashionapp.Profile.ProfilePresenter;
import com.jdpadron98carlosmc98.cheapfashionapp.Profile.ProfileState;
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

import java.lang.ref.WeakReference;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProfilePresenterMockitoTest {
    @Captor
    private ArgumentCaptor<RepositoryContract.OnLogoutCallback> onLogoutCallbackArgumentCaptor;

    @Captor
    private ArgumentCaptor<RepositoryContract.OnGetUserProfileDataCallback> onGetUserProfileDataCallbackArgumentCaptor;

    @Captor
    private ArgumentCaptor<RepositoryContract.ChangeUserDataCallback> changeUserDataCallbackArgumentCaptor;

    @Mock
    private ProfileContract.Model modelMock;

    @Mock
    private ProfileContract.Presenter presenterMock;

    @Mock
    private ProfileContract.Router routerMock;

    @Mock
    private ProfileContract.View viewMock;

    private ProfileState profileState;

    private static final UserData userData = mock(UserData.class);

    private static final String name = "name";

    private static final String phoneNumber = "phoneNumber";

    private static final String UPDATE_MESSAGE = "Successfully updated!";



    @Before
    public void setupForgotPasswordScreen(){
        profileState = new ProfileState();
        // To inject the mocks in the test this method needs to be called
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        presenterMock = new ProfilePresenter(profileState);

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
    public void goToMyProducts(){
        presenterMock.goToMyProducts();
        verify(viewMock).goToMyProducts();
    }

    @Test
    public void goToChangePassword(){
        presenterMock.goToChangePass();
        verify(viewMock).goChangePass();
    }

    @Test
    public void goToHome(){
        presenterMock.goToHome();
        verify(viewMock).goToHome();
    }

    @Test
    public void getUserData(){
        presenterMock.getUserProfileData(userData);
        verify(modelMock).getUserProfileData(eq(userData),onGetUserProfileDataCallbackArgumentCaptor.capture());
        onGetUserProfileDataCallbackArgumentCaptor.getValue().onGetProfileData(false);
        profileState.email = userData.getEmail();
        profileState.phone = userData.getPhoneNumber();
        profileState.name = userData.getName();
        verify(viewMock).updateView(profileState);
    }

    @Test
    public void changeUserData(){
        presenterMock.changeUserData(name,phoneNumber);
        verify(modelMock).changeUserData(eq(name),eq(phoneNumber),changeUserDataCallbackArgumentCaptor.capture());
        changeUserDataCallbackArgumentCaptor.getValue().onChangeUserData(false);
        verify(viewMock).showToast(UPDATE_MESSAGE);
    }
}
