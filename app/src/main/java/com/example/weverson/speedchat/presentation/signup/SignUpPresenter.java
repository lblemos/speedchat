package com.example.weverson.speedchat.presentation.signup;

import android.support.annotation.NonNull;

import com.example.weverson.speedchat.domain.user.User;
import com.example.weverson.speedchat.domain.user.usercase.SignUpUseCase;

import javax.inject.Inject;

import rx.Subscriber;

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
    public void createNewAccount(@NonNull String email, @NonNull String password) {
        User user = new User(email, password);
        mSignUpUseCase.execute(new SignUpUseCase.RequestValues(user)).subscribe(new Subscriber() {
            @Override
            public void onCompleted() {
                mSignUpView.showConfirmationMessage();
            }

            @Override
            public void onError(Throwable e) {
                mSignUpView.showFailMessage(e.getMessage());
            }

            @Override
            public void onNext(Object o) {

            }
        });
    }
}
