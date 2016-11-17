package com.example.weverson.speedchat.login.presentation.signup;

import android.util.Patterns;

import com.example.weverson.speedchat.presentation.signup.SignUpContract;
import com.example.weverson.speedchat.presentation.signup.SignUpPresenter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.rule.PowerMockRule;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({SignUpPresenter.class})
public class UserPresenterTest {

    @Mock
    private SignUpContract.View mSignUpView;

    private SignUpPresenter mSignUpPresenter;

    @Rule
    public PowerMockRule rule = new PowerMockRule();

    @Before
    public void setupMocksAndView() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createNewAccount_emptyNicknameShowErrorUi(){
        mSignUpPresenter = new SignUpPresenter(mSignUpView);
        mSignUpPresenter.createNewAccount("", "john@gmail.com", "123123", "123123");
        verify(mSignUpView).showNicknameErrorMessage(anyString());
    }

    @Test
    public void createNewAccount_emptyEmailShowErrorUi(){
        mSignUpPresenter = new SignUpPresenter(mSignUpView);
        mSignUpPresenter.createNewAccount("john", "", "123123", "123123");
        verify(mSignUpView).showEmailErrorMessage(anyString());
    }

    @Test
    public void createNewAccount_emailNotValidShowErrorUi(){

        when(Patterns.EMAIL_ADDRESS.matcher(anyString()).matches()).thenReturn(false);

        mSignUpPresenter = new SignUpPresenter(mSignUpView);
        mSignUpPresenter.createNewAccount("john", "emailNotValid", "123123", "123123");
        verify(mSignUpView).showEmailErrorMessage(anyString());
    }

    @Test
    public void createNewAccount_emptyPasswordShowErrorUi(){
        mSignUpPresenter = new SignUpPresenter(mSignUpView);
        mSignUpPresenter.createNewAccount("john", "john@gmail.com", "", "123123");
        verify(mSignUpView).showPasswordErrorMessage(anyString());
    }

    @Test
    public void createNewAccount_emptyConfirmPasswordShowErrorUi(){
        mSignUpPresenter = new SignUpPresenter(mSignUpView);
        mSignUpPresenter.createNewAccount("john", "john@gmail.com", "123123", "");
        verify(mSignUpView).showConfirmPasswordErrorMessage(anyString());
    }

    @Test
    public void createNewAccount_passwordDivergentConfirmPasswordShowErrorUi(){
        mSignUpPresenter = new SignUpPresenter(mSignUpView);
        mSignUpPresenter.createNewAccount("john", "john@gmail.com", "123123", "12312");
        verify(mSignUpView).showConfirmPasswordErrorMessage(anyString());
    }
}
