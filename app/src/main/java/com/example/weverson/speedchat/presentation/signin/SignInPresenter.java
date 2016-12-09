package com.example.weverson.speedchat.presentation.signin;


import android.support.annotation.NonNull;

import com.example.weverson.speedchat.domain.user.User;
import com.example.weverson.speedchat.domain.user.interactor.SignInUseCase;

import javax.inject.Inject;

import rx.Subscriber;

import static com.example.weverson.speedchat.utils.Validation.checkEmpty;

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
        boolean emailEmpty = checkEmpty(user.getEmail());
        boolean passwordEmpty = checkEmpty(user.getPassword());

        if(emailEmpty){
            mSignInView.showMessageErrorEmailEmpty();
        }

        if(passwordEmpty) {
            mSignInView.showMessageErrorPasswordEmpty();
        }

        if(!emailEmpty && !passwordEmpty) {
            mSignInUseCase.execute(new SignInUseCase.Request(user))
                    .subscribe(new SignInSubscriber());
        }

    }

    private final class SignInSubscriber extends Subscriber<Void> {
        @Override
        public void onCompleted() {
            mSignInView.openChannels();
        }

        @Override
        public void onError(Throwable e) {
            mSignInView.showFailMessage(e.getMessage());
        }

        @Override
        public void onNext(Void aVoid) {

        }
    }
}
