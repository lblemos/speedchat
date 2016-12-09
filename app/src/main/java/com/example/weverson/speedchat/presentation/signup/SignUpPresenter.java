package com.example.weverson.speedchat.presentation.signup;

import android.support.annotation.NonNull;

import com.example.weverson.speedchat.domain.user.User;
import com.example.weverson.speedchat.domain.user.interactor.SignUpUseCase;

import javax.inject.Inject;

import rx.Subscriber;

import static com.example.weverson.speedchat.utils.Validation.checkEmpty;

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
        boolean emailEmpty = checkEmpty(user.getEmail());
        boolean passwordEmpty = checkEmpty(user.getPassword());

        if (emailEmpty) {
            mSignUpView.showMessageErrorEmailEmpty();
        }

        if (passwordEmpty) {
            mSignUpView.showMessageErrorPasswordEmpty();
        }

        if (!emailEmpty && !passwordEmpty) {
            mSignUpUseCase.execute(new SignUpUseCase.Request(user))
                    .subscribe(new SignSubscriber());
        }

    }

    private final class SignSubscriber extends Subscriber<Void> {

        @Override
        public void onCompleted() {
            mSignUpView.openChannels();
        }

        @Override
        public void onError(Throwable e) {
            mSignUpView.showFailMessage(e.getMessage());
        }

        @Override
        public void onNext(Void aVoid) {

        }
    }
}
