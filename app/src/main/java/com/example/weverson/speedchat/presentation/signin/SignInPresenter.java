package com.example.weverson.speedchat.presentation.signin;


import javax.inject.Inject;

public class SignInPresenter implements SignInContract.Presenter{

    private SignInContract.View mSignInView;

    @Inject
    public SignInPresenter(SignInContract.View signInView) {
        mSignInView = signInView;
    }

    @Inject
    void setupListeners() {
        mSignInView.setPresenter(this);
    }

    @Override
    public void start() {

    }

}
