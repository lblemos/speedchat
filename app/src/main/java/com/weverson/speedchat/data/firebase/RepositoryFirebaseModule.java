package com.weverson.speedchat.data.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.weverson.speedchat.data.firebase.listeners.FirebaseObservableListeners;
import com.weverson.speedchat.data.repository.ChannelRepository;
import com.weverson.speedchat.data.repository.MessageRepository;
import com.weverson.speedchat.data.repository.UserRepository;
import com.weverson.speedchat.utils.dagger.qualifier.Firebase;

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

    @Provides
    @Firebase
    MessageRepository providerMessageFirebaseRepository(DatabaseReference databaseReference,
                                                        FirebaseObservableListeners firebaseObservableListeners) {
        return new MessageFirebaseRepository(databaseReference, firebaseObservableListeners);
    }

}
