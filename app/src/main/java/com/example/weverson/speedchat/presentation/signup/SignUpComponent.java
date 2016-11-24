package com.example.weverson.speedchat.presentation.signup;

import com.example.weverson.speedchat.MainComponent;
import com.example.weverson.speedchat.presentation.scoop.FragmentScoped;

import dagger.Component;

@FragmentScoped
@Component(dependencies = MainComponent.class, modules = SignUpModule.class)
public interface SignUpComponent {

    void inject(SignUpActivity signUpActivity);

}
