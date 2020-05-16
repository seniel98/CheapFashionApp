package com.jdpadron98carlosmc98.cheapfashionapp.presenterTest;


import com.jdpadron98carlosmc98.cheapfashionapp.SignUp.SignUpContract;
import com.jdpadron98carlosmc98.cheapfashionapp.SignUp.SignUpPresenter;
import com.jdpadron98carlosmc98.cheapfashionapp.SignUp.SignUpState;
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
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SignUpPresenterMockitoTest {
    @Captor
    private ArgumentCaptor<RepositoryContract.OnSignUpCallback> signUpCallbackArgumentCaptor;

    @Mock
    private SignUpContract.Model modelMock;

    @Mock
    private SignUpContract.Presenter presenterMock;

    @Mock
    private SignUpContract.Router routerMock;

    @Mock
    private SignUpContract.View viewMock;


    private SignUpState signUpState;

    private static final String EMPTY_STRING = "";

    private static final String NO_ERROR_STRING = "There is no error";

    private static final String name = "prueba";

    private static final String phoneNumber = "12345678";

    private static final String email = "prueba@gmail.com";

    private static final String password = "Prueba123";

    private static final UserData userData = mock(UserData.class);


    @Before
    public void setupSignUpScreen() {
        signUpState = new SignUpState();
        // To inject the mocks in the test this method needs to be called
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        presenterMock = new SignUpPresenter(signUpState);

        // Inject dependencies to the class under test
        presenterMock.injectView(new WeakReference<>(viewMock));
        presenterMock.injectModel(modelMock);
        presenterMock.injectRouter(routerMock);
    }


    @Test
    public void signUpWithEmptyFields() {
        when(userData.getEmail()).thenReturn(EMPTY_STRING);
        when(userData.getName()).thenReturn(EMPTY_STRING);
        when(userData.getPhoneNumber()).thenReturn(EMPTY_STRING);
        presenterMock.signUpClicked(userData, EMPTY_STRING);
        verify(viewMock).showToast(signUpState);
    }

    @Test
    public void signUpNewUserValidFields() {
        when(userData.getEmail()).thenReturn(email);
        when(userData.getName()).thenReturn(name);
        when(userData.getPhoneNumber()).thenReturn(phoneNumber);
        presenterMock.signUpClicked(userData, password);
        verify(modelMock).signUp(eq(userData), eq(password), signUpCallbackArgumentCaptor.capture());
        signUpCallbackArgumentCaptor.getValue().onSignUp(false, NO_ERROR_STRING);
        verify(viewMock).showToast(signUpState);
        verify(viewMock).onBackPressed();
    }
}
