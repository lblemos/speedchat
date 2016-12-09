package com.example.weverson.speedchat.data.firebase;

import com.example.weverson.speedchat.data.repository.UserRepository;
import com.example.weverson.speedchat.domain.abstraction.Authenticable;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import rx.Emitter;
import rx.Observable;

public class UserFirebaseRepository implements UserRepository {

    private FirebaseAuth mAuth;

    @Inject
    public UserFirebaseRepository(FirebaseAuth auth) {
        mAuth = auth;
    }

    @Override
    public Observable<Void> createNewUser(Authenticable authenticable) {

        return Observable.fromEmitter(emitter -> {
            mAuth.createUserWithEmailAndPassword(authenticable.getEmail(), authenticable.getPassword())
                    .addOnSuccessListener(v -> emitter.onCompleted())
                    .addOnFailureListener(emitter::onError);

        }, Emitter.BackpressureMode.BUFFER);
    }

    public Observable<Void> SignIn(Authenticable authenticable) {
        return  Observable.fromEmitter(emitter -> {
            mAuth.signInWithEmailAndPassword(authenticable.getEmail(), authenticable.getPassword())
                    .addOnSuccessListener(v -> emitter.onCompleted())
                    .addOnFailureListener(emitter::onError);
        }, Emitter.BackpressureMode.BUFFER);
    }

}
