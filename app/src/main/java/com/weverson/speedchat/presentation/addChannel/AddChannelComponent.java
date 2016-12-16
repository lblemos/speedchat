package com.weverson.speedchat.presentation.addChannel;

import com.weverson.speedchat.MainComponent;
import com.weverson.speedchat.data.repository.RepositoryComponent;
import com.weverson.speedchat.utils.dagger.scoop.FragmentScoped;

import dagger.Component;

@FragmentScoped
@Component(dependencies = {MainComponent.class, RepositoryComponent.class}, modules = AddChannelModule.class)
public interface AddChannelComponent {

    void inject(AddChannelActivity addChannelActivity);

}
