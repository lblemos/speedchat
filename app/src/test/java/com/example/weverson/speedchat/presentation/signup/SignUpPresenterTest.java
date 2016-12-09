package com.example.weverson.speedchat.presentation.signup;

import com.example.weverson.speedchat.domain.user.interactor.SignUpUseCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SignUpPresenterTest {

    @Mock
    private SignUpContract.View mSignUpView;
    @Mock
    private SignUpUseCase mSignUpUseCase;

    private SignUpPresenter mSignUpPresenter;

    @Before
    public void setupMocks() {

        MockitoAnnotations.initMocks(this);

        mSignUpPresenter = new SignUpPresenter(mSignUpView, mSignUpUseCase);
        mSignUpPresenter.setupListeners();
    }

    @Test
    public void signIn_showMessageErrorEmailEmpty() {

        when(mSignUpUseCase.execute(any(SignUpUseCase.Request.class)))
                .thenReturn(Observable.just(null));

        mSignUpPresenter.createNewAccount("", "test");

        verify(mSignUpView).showMessageErrorEmailEmpty();

    }

    @Test
    public void signIn_showMessageErrorPasswordEmpty() {

        when(mSignUpUseCase.execute(any(SignUpUseCase.Request.class)))
                .thenReturn(Observable.just(null));

        mSignUpPresenter.createNewAccount("test@test.com.br", "");

        verify(mSignUpView).showMessageErrorPasswordEmpty();

    }

    @Test
    public void createNewAccount_openChannels() {

        when(mSignUpUseCase.execute(any(SignUpUseCase.Request.class)))
                .thenReturn(Observable.just(null));


        mSignUpPresenter.createNewAccount("test@test.com.br", "test");

        verify(mSignUpView).openChannels();

    }

    @Test
    public void createNewAccount_showMessageFailUi() {

        when(mSignUpUseCase.execute(any(SignUpUseCase.Request.class)))
                .thenReturn(Observable.error(new Exception("Error")));


        mSignUpPresenter.createNewAccount("test@test.com.com", "test");

        verify(mSignUpView).showFailMessage(anyString());

    }

}
