package com.example.weverson.speedchat.presentation.signin;


import android.support.annotation.NonNull;

import com.example.weverson.speedchat.domain.user.User;
import com.example.weverson.speedchat.domain.user.usercase.SignInUseCase;

import javax.inject.Inject;

import rx.Subscriber;

public class SignInPresenter implements SignInContract.Presenter{

    private SignInContract.View mSignInView;

    private SignInUseCase mSignInUseCase;

    @Inject
    public SignInPresenter(SignInContract.View signInView, SignInUseCase signInUseCase) {
        mSignInView = signInView;
        mSignInUseCase = signInUseCase;
    }

    @Inject
    void setupListeners() {
        mSignInView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void signIn(@NonNull String email, @NonNull String password) {

        User user = new User(email, password);
        mSignInUseCase.execute(new SignInUseCase.RequestValues(user)).subscribe(new Subscriber<Void>() {
            @Override
            public void onCompleted() {
                mSignInView.openChannels();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Void aVoid) {

            }
        });
    }
}
