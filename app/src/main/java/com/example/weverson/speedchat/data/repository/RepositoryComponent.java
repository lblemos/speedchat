package com.example.weverson.speedchat.data.repository;

import com.example.weverson.speedchat.data.firebase.RepositoryFirebaseModule;
import com.example.weverson.speedchat.dagger.qualifier.Firebase;
import dagger.Component;

@Component(modules = RepositoryFirebaseModule.class)
public interface RepositoryComponent {

    @Firebase
    UserRepository getUserRepository();

}
