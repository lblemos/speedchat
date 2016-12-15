package com.example.weverson.speedchat.presentation.signin;

import com.example.weverson.speedchat.domain.user.interactor.AutoSignInUseCase;
import com.example.weverson.speedchat.domain.user.interactor.SignInUseCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class SignInPresenterTest {

    @Mock
    private SignInContract.View mSignInView;

    @Mock
    private SignInUseCase mSignInUseCase;

    @Mock
    private AutoSignInUseCase mAutoSignInUseCase;


    private SignInPresenter mSignInPresenter;

    @Before
    public void setupMocks(){

        initMocks(this);

        mSignInPresenter = new SignInPresenter(mSignInView, mSignInUseCase, mAutoSignInUseCase);
        mSignInPresenter.setupListeners();

    }

    @Test
    public void signIn_showMessageErrorEmailEmpty() {

        when(mSignInUseCase.execute(any(SignInUseCase.Request.class)))
                .thenReturn(Observable.just(null));

        mSignInPresenter.signIn("", "test");

        verify(mSignInView).showMessageErrorEmailEmpty();

    }

    @Test
    public void signIn_showMessageErrorPasswordEmpty() {

        when(mSignInUseCase.execute(any(SignInUseCase.Request.class)))
                .thenReturn(Observable.just(null));

        mSignInPresenter.signIn("test@test.com.br", "");

        verify(mSignInView).showMessageErrorPasswordEmpty();

    }


    @Test
    public void signIn_openActivityChannels() {

        when(mSignInUseCase.execute(any(SignInUseCase.Request.class)))
                .thenReturn(Observable.just(null));

        mSignInPresenter.signIn("teste@teste.com", "teste");

        verify(mSignInView).openChannels();

    }

    @Test
    public void autoSignIn_openActivityChannels() {
        when(mAutoSignInUseCase.execute(any(SignInUseCase.Request.class)))
                .thenReturn(Observable.just(true));

        mSignInPresenter.autoSignIn();

        verify(mSignInView).openChannels();

    }

    @Test
    public void signIn_showFailMessage() {

        when(mSignInUseCase.execute(any(SignInUseCase.Request.class)))
                .thenReturn(Observable.error(new Exception("error")));

        mSignInPresenter.signIn("teste@teste.com", "teste");

        verify(mSignInView).showFailMessage(anyString());

    }

}
