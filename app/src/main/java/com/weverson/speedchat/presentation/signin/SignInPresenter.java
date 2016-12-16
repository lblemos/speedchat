package com.weverson.speedchat.presentation.signin;


import android.support.annotation.NonNull;

import com.weverson.speedchat.domain.user.User;
import com.weverson.speedchat.domain.user.interactor.AutoSignInUseCase;
import com.weverson.speedchat.domain.user.interactor.SignInUseCase;

import javax.inject.Inject;

import rx.Subscriber;

import static com.weverson.speedchat.utils.Validation.checkEmpty;

public class SignInPresenter implements SignInContract.Presenter {

    private final SignInContract.View mSignInView;
    private final SignInUseCase mSignInUseCase;
    private final AutoSignInUseCase mAutoSignInUseCase;

    @Inject
    public SignInPresenter(SignInContract.View signInView, SignInUseCase signInUseCase, AutoSignInUseCase autoSignInUseCase) {
        mSignInView = signInView;
        mSignInUseCase = signInUseCase;
        mAutoSignInUseCase = autoSignInUseCase;
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

        if (emailEmpty) {
            mSignInView.showMessageErrorEmailEmpty();
        }

        if (passwordEmpty) {
            mSignInView.showMessageErrorPasswordEmpty();
        }

        if (!emailEmpty && !passwordEmpty) {
            mSignInUseCase.execute(new SignInUseCase.Request(user))
                    .subscribe(new SignInSubscriber());
        }

    }

    @Override
    public void autoSignIn() {
        mAutoSignInUseCase.execute(new SignInUseCase.Request())
                .subscribe(new AutoSignInSubscriber());
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

    private final class AutoSignInSubscriber extends Subscriber<Boolean> {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onNext(Boolean isAuthenticated) {
            if(isAuthenticated) {
                mSignInView.openChannels();
            }
        }
    }
}
