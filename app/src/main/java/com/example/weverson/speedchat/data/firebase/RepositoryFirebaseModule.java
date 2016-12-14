package com.example.weverson.speedchat.data.firebase;

import com.example.weverson.speedchat.utils.dagger.qualifier.Firebase;
import com.example.weverson.speedchat.data.firebase.listeners.FirebaseObservableListeners;
import com.example.weverson.speedchat.data.repository.ChannelRepository;
import com.example.weverson.speedchat.data.repository.UserRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryFirebaseModule {

    @Provides
    FirebaseAuth providerFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    @Provides
    DatabaseReference providerFirebaseDatabase() {
        return FirebaseDatabase.getInstance().getReference();
    }

    @Provides
    FirebaseObservableListeners providerFirebaseObservableListeners() {
        return new FirebaseObservableListeners();
    }

    @Provides
    @Firebase
    UserRepository providerUserFirebaseRepository(FirebaseAuth firebaseAuth, DatabaseReference databaseReference,
                                                  FirebaseObservableListeners firebaseObservableListeners) {
        return new UserFirebaseRepository(firebaseAuth, databaseReference, firebaseObservableListeners);
    }

    @Provides
    @Firebase
    ChannelRepository providerChannelFirebaseRepository(DatabaseReference databaseReference,
                                                        FirebaseObservableListeners firebaseObservableListeners) {
        return new ChannelFirebaseRepository(databaseReference, firebaseObservableListeners);
    }

}
