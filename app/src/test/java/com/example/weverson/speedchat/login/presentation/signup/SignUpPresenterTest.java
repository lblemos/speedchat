package com.example.weverson.speedchat.login.presentation.signup;

import android.util.Patterns;

import com.example.weverson.speedchat.domain.user.usercase.SignUpUseCase;
import com.example.weverson.speedchat.presentation.signup.SignUpContract;
import com.example.weverson.speedchat.presentation.signup.SignUpPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SignUpPresenter.class)
public class SignUpPresenterTest {

    @Mock private SignUpContract.View mSignUpView;
    @Mock private SignUpUseCase mSignUpUseCase;

    private SignUpPresenter mSignUpPresenter;

    @Before
    public void setupMocks() {

        MockitoAnnotations.initMocks(this);
        mockStatic(Matcher.class);
        mockStatic(Pattern.class);

        mSignUpPresenter = new SignUpPresenter(mSignUpView, mSignUpUseCase);
        mSignUpPresenter.setupListeners();

        PowerMockito.when(Patterns.EMAIL_ADDRESS.matcher(any(String.class)).matches()).thenReturn(true);
    }

    @Test
    public void createNewAccount_emptyNicknameShowErrorUi(){
        mSignUpPresenter.createNewAccount("", "john@gmail.com", "123123", "123123");
        verify(mSignUpView).showNicknameErrorMessage(anyString());
    }

    @Test
    public void createNewAccount_emptyEmailShowErrorUi(){
        mSignUpPresenter.createNewAccount("john", "", "123123", "123123");
        verify(mSignUpView).showEmailErrorMessage(anyString());
    }

    @Test
    public void createNewAccount_emailNotValidShowErrorUi(){
        PowerMockito.when(Patterns.EMAIL_ADDRESS.matcher(anyString()).matches()).thenReturn(false);

        mSignUpPresenter.createNewAccount("john", "emailNotValid", "123123", "123123");
        verify(mSignUpView).showEmailErrorMessage(anyString());
    }

    @Test
    public void createNewAccount_emptyPasswordShowErrorUi(){
        mSignUpPresenter.createNewAccount("john", "john@gmail.com", "", "123123");
        verify(mSignUpView).showPasswordErrorMessage(anyString());
    }

    @Test
    public void createNewAccount_emptyConfirmPasswordShowErrorUi(){
        mSignUpPresenter.createNewAccount("john", "john@gmail.com", "123123", "");
        verify(mSignUpView).showConfirmPasswordErrorMessage(anyString());
    }

    @Test
    public void createNewAccount_passwordDivergentConfirmPasswordShowErrorUi(){
        mSignUpPresenter.createNewAccount("john", "john@gmail.com", "123123", "12312");
        verify(mSignUpView).showConfirmPasswordErrorMessage(anyString());
    }

    @Test
    public void createNewAccount_showMessageInUi(){
        Mockito.when(mSignUpUseCase.execute(any(SignUpUseCase.RequestValues.class)))
                .thenReturn(Observable.just(new Object()));

        mSignUpPresenter.createNewAccount("john", "john@gmail.com", "123123", "123123");

        verify(mSignUpUseCase).execute(any(SignUpUseCase.RequestValues.class));
        verify(mSignUpView).showConfirmationMessage(anyString());
    }

}
