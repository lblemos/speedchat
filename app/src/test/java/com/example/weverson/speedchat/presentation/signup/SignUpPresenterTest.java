package com.example.weverson.speedchat.presentation.signup;

import com.example.weverson.speedchat.domain.user.usercase.SignUpUseCase;

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
    public void createNewAccount_showMessageUi() {

        when(mSignUpUseCase.execute(any(SignUpUseCase.RequestValues.class)))
                .thenReturn(Observable.just(null));

        mSignUpPresenter.createNewAccount("john@gmail.com", "123123");

        verify(mSignUpView).showConfirmationMessage();
    }

    @Test
    public void createNewAccount_showMessageFailUi() {

        when(mSignUpUseCase.execute(any(SignUpUseCase.RequestValues.class)))
                .thenReturn(Observable.error(new Exception()));

        mSignUpPresenter.createNewAccount("john@gmail.com", "1234");

        verify(mSignUpView).showFailMessage(anyString());
    }

}
