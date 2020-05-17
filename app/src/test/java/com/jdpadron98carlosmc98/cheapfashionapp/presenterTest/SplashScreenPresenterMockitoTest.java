package com.jdpadron98carlosmc98.cheapfashionapp.presenterTest;

import com.jdpadron98carlosmc98.cheapfashionapp.SplashScreen.SplashScreenContract;
import com.jdpadron98carlosmc98.cheapfashionapp.SplashScreen.SplashScreenPresenter;
import com.jdpadron98carlosmc98.cheapfashionapp.SplashScreen.SplashScreenState;
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
public class SplashScreenPresenterMockitoTest {
    @Captor
    private ArgumentCaptor<RepositoryContract.OnLoggedInCallback> onLoggedInCallbackArgumentCaptor;

    @Captor
    private ArgumentCaptor<RepositoryContract.OnGetJSONCallback> onGetJSONCallbackArgumentCaptor;

    @Captor
    private ArgumentCaptor<RepositoryContract.onInsertListInDBCallback> onInsertListInDBCallbackArgumentCaptor;

    @Mock
    private SplashScreenContract.Model modelMock;

    @Mock
    private SplashScreenContract.Presenter presenterMock;

    @Mock
    private SplashScreenContract.Router routerMock;

    @Mock
    private SplashScreenContract.View viewMock;

    private SplashScreenState splashScreenState;

    private static final String email = "jdpadron98@gmail.com";
    private static final String pass = "Jdpadron98";

    private static final List<ProductItem> itemList = mock(List.class);


    @Before
    public void setupLoginScreen() {
        splashScreenState = new SplashScreenState();
        // To inject the mocks in the test this method needs to be called
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        presenterMock = new SplashScreenPresenter(splashScreenState);

        // Inject dependencies to the class under test
        presenterMock.injectView(new WeakReference<>(viewMock));
        presenterMock.injectModel(modelMock);
        presenterMock.injectRouter(routerMock);
    }

    @Test
    public void loadDataFromRepository(){
        presenterMock.checkSession();
        verify(modelMock).checkSession(onLoggedInCallbackArgumentCaptor.capture());
        onLoggedInCallbackArgumentCaptor.getValue().onLoggedIn(true);
        verify(modelMock).getDataFromRepository(onGetJSONCallbackArgumentCaptor.capture());
        onGetJSONCallbackArgumentCaptor.getValue().onGetJSON(false, itemList);
        verify(modelMock).insertListInDb(onInsertListInDBCallbackArgumentCaptor.capture(), eq(itemList));
        onInsertListInDBCallbackArgumentCaptor.getValue().onInsert(false);
        verify(viewMock).goToHome();

    }

    @Test
    public void noUserLogged(){
        presenterMock.checkSession();
        verify(modelMock).checkSession(onLoggedInCallbackArgumentCaptor.capture());
        onLoggedInCallbackArgumentCaptor.getValue().onLoggedIn(false);
        verify(viewMock).goToLogin();
    }
}
