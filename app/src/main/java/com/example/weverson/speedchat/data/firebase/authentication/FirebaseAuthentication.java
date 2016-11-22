package com.example.weverson.speedchat.data.firebase.authentication;

import com.example.weverson.speedchat.data.Authentication;
import com.example.weverson.speedchat.domain.Authenticable;
import com.example.weverson.speedchat.domain.user.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

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

    @Override
    public Observable<User> updateProfile(Authenticable authenticable) {
        FirebaseUser user = mAuth.getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(authenticable.getNickname())
                .setPhotoUri(authenticable.getPhotoUri())
                .build();

        return Observable.create(subscriber -> {
            user.updateProfile(profileUpdates).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    subscriber.onCompleted();
                }
            }).addOnFailureListener(e -> subscriber.onError(e));
        });

    }

}
