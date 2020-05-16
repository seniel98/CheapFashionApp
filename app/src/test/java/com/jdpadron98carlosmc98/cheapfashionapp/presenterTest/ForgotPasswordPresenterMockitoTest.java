package com.jdpadron98carlosmc98.cheapfashionapp.presenterTest;


import com.jdpadron98carlosmc98.cheapfashionapp.ForgotPassword.ForgotPasswordContract;
import com.jdpadron98carlosmc98.cheapfashionapp.ForgotPassword.ForgotPasswordPresenter;
import com.jdpadron98carlosmc98.cheapfashionapp.ForgotPassword.ForgotPasswordState;
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
public class ForgotPasswordPresenterMockitoTest {
    @Captor
    private ArgumentCaptor<RepositoryContract.onForgetPasswordCallback> onForgetPasswordCallbackArgumentCaptor;

    @Mock
    private ForgotPasswordContract.Model modelMock;

    @Mock
    private ForgotPasswordContract.Presenter presenterMock;

    @Mock
    private ForgotPasswordContract.Router routerMock;

    @Mock
    private ForgotPasswordContract.View viewMock;


    private ForgotPasswordState forgotPasswordState;

    private static final String phoneNumber = "12345678";

    private static final String email = "prueba@gmail.com";


    @Before
    public void setupForgotPasswordScreen(){
        forgotPasswordState = new ForgotPasswordState();
        // To inject the mocks in the test this method needs to be called
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        presenterMock = new ForgotPasswordPresenter(forgotPasswordState);

        // Inject dependencies to the class under test
        presenterMock.injectView(new WeakReference<>(viewMock));
        presenterMock.injectModel(modelMock);
        presenterMock.injectRouter(routerMock);
    }

    @Test
    public void onSendEmail(){
        presenterMock.onSendClicked(email);
        verify(modelMock).onForgetPassword(eq(email), onForgetPasswordCallbackArgumentCaptor.capture());
        onForgetPasswordCallbackArgumentCaptor.getValue().onForgetPassword(false);
        verify(viewMock).displayResult(forgotPasswordState);
    }


}
