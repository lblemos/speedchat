package com.example.weverson.speedchat.data.firebase;

import com.example.weverson.speedchat.data.repository.UserRepository;
import com.example.weverson.speedchat.dagger.qualifier.Firebase;
import com.google.firebase.auth.FirebaseAuth;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryFirebaseModule {

    @Provides
    FirebaseAuth providerFirebaseAuth(){
        return FirebaseAuth.getInstance();
    }


    @Provides
    @Firebase
    UserRepository providerUserFirebaseRepository(FirebaseAuth firebaseAuth){
        return new UserFirebaseRepository(firebaseAuth);
    }

}
