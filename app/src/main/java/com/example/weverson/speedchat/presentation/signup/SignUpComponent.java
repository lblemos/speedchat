package com.example.weverson.speedchat.presentation.signup;

import com.example.weverson.speedchat.data.firebase.FirebaseComponent;
import com.example.weverson.speedchat.data.firebase.FirebaseModule;
import com.example.weverson.speedchat.scopes.FragmentScoped;

import dagger.Component;

@FragmentScoped
@Component(dependencies = FirebaseComponent.class, modules = SignUpModule.class)
public interface SignUpComponent {

    void inject(SignUpActivity signUpActivity);

}
