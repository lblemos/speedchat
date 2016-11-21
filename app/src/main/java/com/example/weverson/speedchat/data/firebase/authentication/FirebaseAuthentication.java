package com.example.weverson.speedchat.data.firebase.authentication;

import com.example.weverson.speedchat.data.Authentication;
import com.example.weverson.speedchat.domain.Authenticable;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

public class FirebaseAuthentication implements Authentication {

    private FirebaseAuth mAuth;

    @Inject
    public FirebaseAuthentication(FirebaseAuth auth) {
        mAuth = auth;
    }

    @Override
    public void createNewUser(Authenticable authenticable) {
        mAuth.createUserWithEmailAndPassword(authenticable.getEmail(), authenticable.getPassword());
    }
    
}
