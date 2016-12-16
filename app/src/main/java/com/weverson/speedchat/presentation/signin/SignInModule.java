package com.weverson.speedchat.presentation.signin;

import dagger.Module;
import dagger.Provides;

@Module
public class SignInModule {

    private SignInContract.View mSignIn;

    public SignInModule(SignInContract.View signIn) {
        mSignIn = signIn;
    }

    @Provides
    public SignInContract.View providerSignIn(){
        return mSignIn;
    }

}
