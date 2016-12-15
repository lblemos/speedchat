package com.example.weverson.speedchat.data.firebase;

import com.example.weverson.speedchat.data.exception.AuthenticableException;
import com.example.weverson.speedchat.data.firebase.listeners.FirebaseObservableListeners;
import com.example.weverson.speedchat.data.repository.UserRepository;
import com.example.weverson.speedchat.domain.abstraction.Authenticable;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import javax.inject.Inject;

import rx.Observable;

public class UserFirebaseRepository implements UserRepository {

    private FirebaseAuth mAuth;
    DatabaseReference mReference;
    FirebaseObservableListeners mFirebaseObservableListeners;

    @Inject
    public UserFirebaseRepository(FirebaseAuth auth, DatabaseReference reference,
                                  FirebaseObservableListeners firebaseObservableListeners) {
        mAuth = auth;
        mReference = reference;
        mFirebaseObservableListeners = firebaseObservableListeners;
    }

    @Override
    public Observable<Void> createNewUser(Authenticable authenticable) {

        return Observable.create(subscriber -> {
            mAuth.createUserWithEmailAndPassword(authenticable.getEmail(), authenticable.getPassword())
                    .addOnSuccessListener(v -> subscriber.onCompleted())
                    .addOnFailureListener(subscriber::onError);
        });
    }

    public Observable<Void> SignIn(Authenticable authenticable) {
        return Observable.create(subscriber -> {
            if (authenticable != null) {
                mAuth.signInWithEmailAndPassword(authenticable.getEmail(), authenticable.getPassword())
                        .addOnSuccessListener(v -> subscriber.onCompleted())
                        .addOnFailureListener(subscriber::onError);
            } else {
                subscriber.onError(new AuthenticableException("Authenticable not found"));
            }
        });
    }

    @Override
    public Observable<Authenticable> fetchCurrentUser(Authenticable authenticable) {

        return Observable.create(subscriber -> {
            FirebaseUser firebaseUser = mAuth.getCurrentUser();
            if (firebaseUser != null) {
                authenticable.setUid(firebaseUser.getUid());
                authenticable.setEmail(firebaseUser.getEmail());
            }

            subscriber.onNext(authenticable);
            subscriber.onCompleted();

        });

    }

}
