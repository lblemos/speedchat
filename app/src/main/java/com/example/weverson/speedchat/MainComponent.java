package com.example.weverson.speedchat;

import android.content.Context;

import com.example.weverson.speedchat.data.firebase.FirebaseModule;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MainModule.class, FirebaseModule.class})
public interface MainComponent {

    Context getContext();

    FirebaseAuth getFirebaseAuth();
}
