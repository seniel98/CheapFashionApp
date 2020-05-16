package com.jdpadron98carlosmc98.cheapfashionapp.modelTest;

import com.jdpadron98carlosmc98.cheapfashionapp.Login.LoginContract;
import com.jdpadron98carlosmc98.cheapfashionapp.Login.LoginModel;
import com.jdpadron98carlosmc98.cheapfashionapp.data.Repository;
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


    private static final String EMAIL = "jdpadron98@gmail.com";

    private static final String PASSWORD = "Jdpadron98";


    @Before
    public void setupLoginModelTests() {
        // To inject the mocks in the test this method needs to be called
        MockitoAnnotations.initMocks(this);


        model = new LoginModel(repositoryMock);

    }




    @Test
    public void verifyLogin() {

        model.signIn(EMAIL, PASSWORD, signInCallbackMock);

        verify(repositoryMock).signIn(eq(EMAIL), eq(PASSWORD), onSignInCallbackArgumentCaptor.capture());

    }

}
