package com.weverson.speedchat.presentation.signin;

import com.weverson.speedchat.MainComponent;
import com.weverson.speedchat.data.repository.RepositoryComponent;
import com.weverson.speedchat.utils.dagger.scoop.FragmentScoped;

import dagger.Component;

@FragmentScoped
@Component(dependencies = {MainComponent.class, RepositoryComponent.class}, modules = SignInModule.class)
public interface SignInComponent {

    void inject(SignInActivity signInActivity);

}
