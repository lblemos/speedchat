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

    private void createNewAccountFail(Throwable throwable) {
        mSignUpView.showFailMessage(throwable.getMessage());
    }

    private void createNewAccountSuccess(){
        mSignUpView.showConfirmationMessage();
    }


    @Override
    public void createNewAccount(@NonNull String email, @NonNull String password) {
        User user = new User(email, password);
        mSignUpUseCase.execute(new SignUpUseCase.RequestValues(user))
                .subscribe(v -> {}, this::createNewAccountFail, this::createNewAccountSuccess);
    }
}
