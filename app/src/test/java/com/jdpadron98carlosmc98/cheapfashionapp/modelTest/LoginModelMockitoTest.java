package com.jdpadron98carlosmc98.cheapfashionapp.modelTest;

import com.jdpadron98carlosmc98.cheapfashionapp.Login.LoginContract;
import com.jdpadron98carlosmc98.cheapfashionapp.Login.LoginModel;
import com.jdpadron98carlosmc98.cheapfashionapp.data.RepositoryContract;

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
public class LoginModelMockitoTest {

    @Captor
    private ArgumentCaptor<RepositoryContract.OnSignInCallback> onSignInCallbackArgumentCaptor;


    @Mock
    private RepositoryContract repositoryMock;


    private RepositoryContract.OnSignInCallback signInCallbackMock = mock(RepositoryContract.OnSignInCallback.class);

    private LoginContract.Model model;


    private static final String email = "jdpadron98@gmail.com";

    private static final String password = "Jdpadron98";


    @Before
    public void setup() {
        // To inject the mocks in the test this method needs to be called
        MockitoAnnotations.initMocks(this);


        model = new LoginModel(repositoryMock);

    }




    @Test
    public void verifyLogin() {

        model.signIn(email, password, signInCallbackMock);

        verify(repositoryMock).signIn(eq(email), eq(password), onSignInCallbackArgumentCaptor.capture());

    }

}
