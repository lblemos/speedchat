package com.example.weverson.speedchat.presentation.signup;

import android.support.annotation.NonNull;
import android.util.Patterns;

import javax.inject.Inject;

public class SignUpPresenter implements SignUpContract.Presenter{

    private SignUpContract.View mSignUpView;

    @Inject
    public SignUpPresenter(SignUpContract.View view) {
        mSignUpView = view;
    }

    @Inject
    void setupListeners(){
        mSignUpView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void createNewAccount(@NonNull String nickname, @NonNull String email,
                                 @NonNull String password, @NonNull String confirmPassword) {

        if(nickname.isEmpty()){
            mSignUpView.showNicknameErrorMessage("The name can not be empty");
        }

        if(email.isEmpty()){
            mSignUpView.showEmailErrorMessage("The email can not be empty");
        }

        if(password.isEmpty()) {
            mSignUpView.showPasswordErrorMessage("The password can not be empty");
        }

        if(Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mSignUpView.showEmailErrorMessage("The email not valid");
        }

        if(confirmPassword.isEmpty()) {
            mSignUpView.showConfirmPasswordErrorMessage("The Confirm password can not be empty");
        }else if(!password.equals(confirmPassword)){
            mSignUpView.showConfirmPasswordErrorMessage("The passwords are not the same");
        }

    }


}
