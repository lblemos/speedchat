package com.example.weverson.speedchat.presentation.channels;

import com.example.weverson.speedchat.MainComponent;
import com.example.weverson.speedchat.utils.dagger.scoop.FragmentScoped;
import com.example.weverson.speedchat.data.repository.RepositoryComponent;

import dagger.Component;

@FragmentScoped
@Component(dependencies = {MainComponent.class, RepositoryComponent.class}, modules = {ChannelsModule.class})
public interface ChannelsComponent {

    void inject(ChannelsActivity channelsActivity);

}
