package com.weverson.speedchat.presentation.signup;

import com.weverson.speedchat.MainComponent;
import com.weverson.speedchat.data.repository.RepositoryComponent;
import com.weverson.speedchat.utils.dagger.scoop.FragmentScoped;

import dagger.Component;

@FragmentScoped
@Component(dependencies = {MainComponent.class, RepositoryComponent.class}, modules = SignUpModule.class)
public interface SignUpComponent {

    void inject(SignUpActivity signUpActivity);

}
