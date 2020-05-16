package com.jdpadron98carlosmc98.cheapfashionapp.modelTest;

import com.jdpadron98carlosmc98.cheapfashionapp.ChangePassword.ChangePasswordContract;
import com.jdpadron98carlosmc98.cheapfashionapp.ChangePassword.ChangePasswordModel;
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
public class ChangePasswordModelMockitoTest {

    @Captor
    private ArgumentCaptor<RepositoryContract.onChangePasswordCallback> onChangePasswordCallbackArgumentCaptor;

    @Mock
    private RepositoryContract repository;

    private ChangePasswordContract.Model model;

    private RepositoryContract.onChangePasswordCallback onChangePasswordCallbackMock = mock(RepositoryContract.onChangePasswordCallback.class);

    private final static String currentPasswordText = "Jdpadron98";

    private final static String newPasswordText = "JDpadron98";

    @Before
    public void setup() {
        // To inject the mocks in the test this method needs to be called
        MockitoAnnotations.initMocks(this);

        model = new ChangePasswordModel(repository);
    }


    @Test
    public void verifyOnChangePassword() {
        model.onChangePassword(currentPasswordText, newPasswordText, onChangePasswordCallbackMock);
        verify(repository).changePassword(eq(currentPasswordText), eq(newPasswordText), onChangePasswordCallbackArgumentCaptor.capture());
    }

}
