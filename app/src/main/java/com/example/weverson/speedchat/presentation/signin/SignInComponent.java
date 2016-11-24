package com.example.weverson.speedchat.presentation.signin;

import com.example.weverson.speedchat.MainComponent;
import com.example.weverson.speedchat.presentation.scoop.FragmentScoped;

import dagger.Component;

@FragmentScoped
@Component(dependencies = MainComponent.class, modules = SignInModule.class)
public interface SignInComponent {

    void inject(SignInActivity signInActivity);

}
