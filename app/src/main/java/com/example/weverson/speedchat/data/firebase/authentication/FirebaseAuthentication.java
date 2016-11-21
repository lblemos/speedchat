package com.example.weverson.speedchat.data.firebase.authentication;

import com.example.weverson.speedchat.data.Authentication;
import com.example.weverson.speedchat.domain.Authenticable;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

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

    @Override
    public Observable<Void> updateProfile(Authenticable authenticable) {
        FirebaseUser user = mAuth.getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(authenticable.getNickname())
                .setPhotoUri(authenticable.getPhotoUri())
                .build();

        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(Subscriber<? super Void> subscriber) {
                user.updateProfile(profileUpdates).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        subscriber.onCompleted();
                    }
                });
            }
        });

    }

}
