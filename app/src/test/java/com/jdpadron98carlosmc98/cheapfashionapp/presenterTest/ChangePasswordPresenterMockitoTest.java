package com.jdpadron98carlosmc98.cheapfashionapp.presenterTest;

import com.jdpadron98carlosmc98.cheapfashionapp.ChangePassword.ChangePasswordContract;
import com.jdpadron98carlosmc98.cheapfashionapp.ChangePassword.ChangePasswordPresenter;
import com.jdpadron98carlosmc98.cheapfashionapp.ChangePassword.ChangePasswordState;
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
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ChangePasswordPresenterMockitoTest {
    @Captor
    private ArgumentCaptor<RepositoryContract.onChangePasswordCallback> onChangePasswordCallbackArgumentCaptor;

    @Mock
    private ChangePasswordContract.Model modelMock;

    @Mock
    private ChangePasswordContract.Presenter presenterMock;

    @Mock
    private ChangePasswordContract.Router routerMock;

    @Mock
    private ChangePasswordContract.View viewMock;

    private ChangePasswordState changePasswordState;

    private static final String password = "Prueba123";

    private static final String newPassword = "PRueba123";

    private static final String CHANGE_PASSWORD = "Password changed";


    @Before
    public void setupForgotPasswordScreen(){
        changePasswordState = new ChangePasswordState();
        // To inject the mocks in the test this method needs to be called
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        presenterMock = new ChangePasswordPresenter(changePasswordState);

        // Inject dependencies to the class under test
        presenterMock.injectView(new WeakReference<>(viewMock));
        presenterMock.injectModel(modelMock);
        presenterMock.injectRouter(routerMock);
    }


    @Test
    public void changePasswordSuccessfully(){
        presenterMock.onSaveClicked(password, newPassword);
        verify(modelMock).onChangePassword(eq(password),eq(newPassword), onChangePasswordCallbackArgumentCaptor.capture());
        onChangePasswordCallbackArgumentCaptor.getValue().onChangePassword(false, CHANGE_PASSWORD);
        verify(viewMock).displayResult(changePasswordState);
        verify(viewMock).onBackPressed();
    }
}
