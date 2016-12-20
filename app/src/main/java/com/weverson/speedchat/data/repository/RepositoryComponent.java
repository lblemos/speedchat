package com.weverson.speedchat.data.repository;

import com.weverson.speedchat.data.firebase.RepositoryFirebaseModule;
import com.weverson.speedchat.utils.dagger.qualifier.Firebase;
import dagger.Component;

@Component(modules = RepositoryFirebaseModule.class)
public interface RepositoryComponent {

    @Firebase
    UserRepository getUserRepository();

    @Firebase
    ChannelRepository getChannelRepository();

    @Firebase
    MessageRepository getMessageRepository();

}
