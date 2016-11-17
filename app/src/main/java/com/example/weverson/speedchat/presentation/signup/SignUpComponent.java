package com.example.weverson.speedchat.presentation.signup;

import com.example.weverson.speedchat.scopes.FragmentScoped;

import dagger.Component;

@FragmentScoped
@Component(modules = {SignUpModule.class})
public interface SignUpComponent {

    void inject(SignUpActivity signUpActivity);

}
