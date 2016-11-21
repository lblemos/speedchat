package com.example.weverson.speedchat.data.firebase;

import com.example.weverson.speedchat.data.firebase.authentication.FirebaseAuthentication;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = FirebaseModule.class)
public interface FirebaseComponent {

    FirebaseAuthentication getFirebaseAuthentication();

}
