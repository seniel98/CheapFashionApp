package com.jdpadron98carlosmc98.cheapfashionapp.presenterTest;

import com.jdpadron98carlosmc98.cheapfashionapp.Login.LoginContract;
import com.jdpadron98carlosmc98.cheapfashionapp.Login.LoginPresenter;
import com.jdpadron98carlosmc98.cheapfashionapp.Login.LoginState;
import com.jdpadron98carlosmc98.cheapfashionapp.data.ProductItem;
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
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterMockitoTest {
    @Captor
    private ArgumentCaptor<RepositoryContract.OnSignInCallback> signInCallbackArgumentCaptor;

    @Captor
    private ArgumentCaptor<RepositoryContract.OnGetJSONCallback> onGetJSONCallbackArgumentCaptor;

    @Captor
    private ArgumentCaptor<RepositoryContract.onInsertListInDBCallback> onInsertListInDBCallbackArgumentCaptor;

    @Mock
    private LoginContract.Model modelMock;

    @Mock
    private LoginContract.Presenter presenterMock;

    @Mock
    private LoginContract.Router routerMock;

    @Mock
    private LoginContract.View viewMock;

    private LoginState loginState;

    private static final String email = "jdpadron98@gmail.com";
    private static final String pass = "Jdpadron98";

    private static final List<ProductItem> itemList = mock(List.class);


    @Before
    public void setupLoginScreen(){
        loginState = new LoginState();
        // To inject the mocks in the test this method needs to be called
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        presenterMock = new LoginPresenter(loginState);

        // Inject dependencies to the class under test
        presenterMock.injectView(new WeakReference<>(viewMock));
        presenterMock.injectModel(modelMock);
        presenterMock.injectRouter(routerMock);
    }

    @Test
    public void goToSignUp(){
        presenterMock.goToSignUpRouter();
        verify(viewMock).goToSignUp();
    }

    @Test
    public void goToForgotPassword(){
        presenterMock.goToForgotPasswordRouter();
        verify(viewMock).goToForgotPassword();
    }

    @Test
    public void signInWithoutRegisteredUser(){
        presenterMock.checkLogin(email,pass);
        verify(modelMock).signIn(eq(email),eq(pass),signInCallbackArgumentCaptor.capture());
        signInCallbackArgumentCaptor.getValue().onSignIn(true);
        verify(viewMock).displayData(loginState);
    }
    @Test
    public void errorNoPassword(){
        presenterMock.checkLogin(email,"");
        //1 == no password
        verify(viewMock).setErrorLayoutInputs(1);
    }
    @Test
    public void errorNoEmail(){
        presenterMock.checkLogin("",pass);
        //1 == no password
        verify(viewMock).setErrorLayoutInputs(0);
    }
    @Test
    public void errorNoEmailAndNoPass(){
        presenterMock.checkLogin("","");
        //1 == no password
        verify(viewMock).setErrorLayoutInputs(2);
    }

    @Test
    public void loadDataFromRepository(){
        presenterMock.signIn(email,pass);
        verify(modelMock).signIn(eq(email),eq(pass),signInCallbackArgumentCaptor.capture());
        signInCallbackArgumentCaptor.getValue().onSignIn(false);
        verify(modelMock).getDataFromRepository(onGetJSONCallbackArgumentCaptor.capture());
        onGetJSONCallbackArgumentCaptor.getValue().onGetJSON(false, itemList);
        verify(modelMock).insertListInDb(onInsertListInDBCallbackArgumentCaptor.capture(), eq(itemList));
        onInsertListInDBCallbackArgumentCaptor.getValue().onInsert(false);
        verify(viewMock).goToHome();
    }
}
