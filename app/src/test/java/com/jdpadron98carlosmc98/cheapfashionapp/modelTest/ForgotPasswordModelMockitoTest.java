package com.jdpadron98carlosmc98.cheapfashionapp.modelTest;


import com.jdpadron98carlosmc98.cheapfashionapp.ForgotPassword.ForgotPasswordContract;
import com.jdpadron98carlosmc98.cheapfashionapp.ForgotPassword.ForgotPasswordModel;
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
public class ForgotPasswordModelMockitoTest {

    @Captor
    private ArgumentCaptor<RepositoryContract.onForgetPasswordCallback> onForgetPasswordCallbackArgumentCaptor;

    @Mock
    private RepositoryContract repository;

    private ForgotPasswordContract.Model model;

    private RepositoryContract.onForgetPasswordCallback onForgetPasswordCallbackMock = mock(RepositoryContract.onForgetPasswordCallback.class);

    private static final String email = "dummy@email.com";

    @Before
    public void setup() {
        // To inject the mocks in the test this method needs to be called
        MockitoAnnotations.initMocks(this);

        model = new ForgotPasswordModel(repository);
    }


    @Test
    public void verifyOnForgetPassword() {
        model.onForgetPassword(email, onForgetPasswordCallbackMock);
        verify(repository).forgetPassword(eq(email), onForgetPasswordCallbackArgumentCaptor.capture());
    }
}
