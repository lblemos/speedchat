package com.weverson.speedchat.presentation.channels;

import com.weverson.speedchat.MainComponent;
import com.weverson.speedchat.utils.dagger.scoop.FragmentScoped;
import com.weverson.speedchat.data.repository.RepositoryComponent;

import dagger.Component;

@FragmentScoped
@Component(dependencies = {MainComponent.class, RepositoryComponent.class}, modules = {ChannelsModule.class})
public interface ChannelsComponent {

    void inject(ChannelsActivity channelsActivity);

}
