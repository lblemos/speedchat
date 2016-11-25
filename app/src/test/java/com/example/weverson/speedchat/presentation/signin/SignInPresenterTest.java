package com.example.weverson.speedchat.presentation.signin;

import com.example.weverson.speedchat.domain.user.usercase.SignInUseCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class SignInPresenterTest {

    @Mock
    private SignInContract.View mSignInView;

    @Mock
    private SignInUseCase mSignInUseCase;

    private SignInPresenter mSignInPresenter;

    @Before
    public void setupMocks(){

        initMocks(this);

        mSignInPresenter = new SignInPresenter(mSignInView, mSignInUseCase);
        mSignInPresenter.setupListeners();

    }

    @Test
    public void signIn_openActivityChannels() {

        when(mSignInUseCase.execute(any(SignInUseCase.RequestValues.class)))
                .thenReturn(Observable.just(null));

        mSignInPresenter.signIn("teste@teste.com", "teste");

        verify(mSignInView).openChannels();

    }



}
