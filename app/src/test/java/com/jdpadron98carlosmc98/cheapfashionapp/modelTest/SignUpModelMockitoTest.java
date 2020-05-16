package com.jdpadron98carlosmc98.cheapfashionapp.modelTest;

import com.jdpadron98carlosmc98.cheapfashionapp.R;
import com.jdpadron98carlosmc98.cheapfashionapp.SignUp.SignUpContract;
import com.jdpadron98carlosmc98.cheapfashionapp.SignUp.SignUpModel;
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
public class SignUpModelMockitoTest {

    @Captor
    private ArgumentCaptor<RepositoryContract.OnSignUpCallback> onSignUpCallbackArgumentCaptor;


    @Mock
    private RepositoryContract repositoryMock;


    private RepositoryContract.OnSignUpCallback signUpCallbackMock = mock(RepositoryContract.OnSignUpCallback.class);



    private SignUpContract.Model model;


    private static final UserData userDataMock = mock(UserData.class);

    private static final String PASSWORD = "Jdpadron98";



    @Before
    public void setupSignUpScreen() {
        // To inject the mocks in the test this method needs to be called
        MockitoAnnotations.initMocks(this);

        model = new SignUpModel(repositoryMock);
    }


    @Test
    public void verifySignUp() {
        model.signUp(userDataMock, PASSWORD, signUpCallbackMock);
        verify(repositoryMock).signUp(eq(userDataMock), eq(PASSWORD), onSignUpCallbackArgumentCaptor.capture());
    }

}
