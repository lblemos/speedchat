package com.weverson.speedchat.presentation.messages;

import com.weverson.speedchat.MainComponent;
import com.weverson.speedchat.data.repository.RepositoryComponent;
import com.weverson.speedchat.utils.dagger.scoop.FragmentScoped;

import dagger.Component;

@FragmentScoped
@Component(dependencies = {MainComponent.class, RepositoryComponent.class}, modules = MessagesModule.class)
public interface MessagesComponent {

    void inject(MessagesActivity messagesActivity);

}
