package com.example.weverson.speedchat.presentation.signup;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Patterns;

import com.example.weverson.speedchat.domain.user.User;
import com.example.weverson.speedchat.domain.user.usercase.SignUpUseCase;

import javax.inject.Inject;

public class SignUpPresenter implements SignUpContract.Presenter {

    private SignUpContract.View mSignUpView;

    private SignUpUseCase mSignUpUseCase;

    private Context mContext;

    @Inject
    public SignUpPresenter(SignUpContract.View view, SignUpUseCase signUpUseCase, Context context) {
        mContext = context;
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
                    .subscribe(v -> {}, this::createNewAccountFail, this::createNewAccountSuccess);
        }

    }

    private void createNewAccountFail(Throwable throwable) {
        mSignUpView.showFailMessage(throwable.getMessage());
    }

    private void createNewAccountSuccess(){
        mSignUpView.showConfirmationMessage();
    }

    private boolean isValidForm(@NonNull String nickname, @NonNull String email,
                                @NonNull String password, @NonNull String confirmPassword) {

        boolean isValid = true;

        if (nickname.isEmpty()) {
            mSignUpView.showNicknameErrorMessage();
            isValid = false;
        }

        if (email.isEmpty()) {
            mSignUpView.showEmailErrorMessage();
            isValid = false;
        }

        if (password.isEmpty()) {
            mSignUpView.showPasswordErrorMessage();
            isValid = false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mSignUpView.showEmailErrorMessage();
            isValid = false;
        }

        if (confirmPassword.isEmpty()) {
            mSignUpView.showConfirmPasswordErrorMessage();
            isValid = false;

        } else if (!password.equals(confirmPassword)) {
            mSignUpView.showConfirmPasswordErrorMessage();
            isValid = false;
        }

        return isValid;
    }


}
