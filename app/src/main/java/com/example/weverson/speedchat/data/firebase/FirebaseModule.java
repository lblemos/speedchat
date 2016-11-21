package com.example.weverson.speedchat.data.firebase;

import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FirebaseModule {

    @Provides
    @Singleton
    FirebaseAuth providerFirebaseAuth(){
        return FirebaseAuth.getInstance();
    }

}
