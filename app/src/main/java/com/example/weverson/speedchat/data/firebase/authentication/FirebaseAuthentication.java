package com.example.weverson.speedchat.data.firebase.authentication;

import com.example.weverson.speedchat.data.Authentication;
import com.example.weverson.speedchat.domain.Authenticable;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import rx.Observable;

public class FirebaseAuthentication implements Authentication {

    private FirebaseAuth mAuth;

    @Inject
    public FirebaseAuthentication(FirebaseAuth auth) {
        mAuth = auth;
    }

    @Override
    public Observable<Void> createNewUser(Authenticable authenticable) {

        return Observable.create(subscriber -> {
            mAuth.createUserWithEmailAndPassword(authenticable.getEmail(), authenticable.getPassword())
                    .addOnSuccessListener(v -> subscriber.onCompleted())
                    .addOnFailureListener(e -> subscriber.onError(e));

        });

    }

}
