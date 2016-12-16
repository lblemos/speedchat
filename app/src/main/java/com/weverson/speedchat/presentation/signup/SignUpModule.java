package com.weverson.speedchat.presentation.signup;

import dagger.Module;
import dagger.Provides;

@Module
public class SignUpModule {

    private final SignUpContract.View mView;

    public SignUpModule(SignUpContract.View view) {
        mView = view;
    }

    @Provides
    SignUpContract.View providerSignUpView() {
        return mView;
    }
}
