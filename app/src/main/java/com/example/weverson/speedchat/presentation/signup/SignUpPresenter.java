package com.example.weverson.speedchat.presentation.signup;

import android.support.annotation.NonNull;
import android.util.Patterns;

import com.example.weverson.speedchat.domain.user.User;
import com.example.weverson.speedchat.domain.user.usercase.SignUpUseCase;

import javax.inject.Inject;

public class SignUpPresenter implements SignUpContract.Presenter {

    private SignUpContract.View mSignUpView;

    private SignUpUseCase mSignUpUseCase;

    @Inject
    public SignUpPresenter(SignUpContract.View view, SignUpUseCase signUpUseCase) {
        mSignUpView = view;
        mSignUpUseCase = signUpUseCase;
    }

    @Inject
    public void setupListeners() {
        mSignUpView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void createNewAccount(@NonNull String nickname, @NonNull String email,
                                 @NonNull String password, @NonNull String confirmPassword) {

        if(isValidForm(nickname, email, password, confirmPassword)) {

            User user = new User(nickname, email, password);
            mSignUpUseCase.execute(new SignUpUseCase.RequestValues(user))
                    .subscribe(v -> {}, v -> {}, this::createNewAccountCompleted);
        }

    }

    private void createNewAccountCompleted() {
        mSignUpView.showConfirmationMessage("Account created with success!!!");
    }

    private boolean isValidForm(@NonNull String nickname, @NonNull String email,
                                @NonNull String password, @NonNull String confirmPassword) {

        boolean isValid = true;

        if (nickname.isEmpty()) {
            mSignUpView.showNicknameErrorMessage("The name can not be empty");
            isValid = false;
        }

        if (email.isEmpty()) {
            mSignUpView.showEmailErrorMessage("The email can not be empty");
            isValid = false;
        }

        if (password.isEmpty()) {
            mSignUpView.showPasswordErrorMessage("The password can not be empty");
            isValid = false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mSignUpView.showEmailErrorMessage("The email not valid");
            isValid = false;
        }

        if (confirmPassword.isEmpty()) {
            mSignUpView.showConfirmPasswordErrorMessage("The Confirm password can not be empty");
            isValid = false;
        } else if (!password.equals(confirmPassword)) {
            mSignUpView.showConfirmPasswordErrorMessage("The passwords are not the same");
            isValid = false;
        }

        return isValid;
    }


}
