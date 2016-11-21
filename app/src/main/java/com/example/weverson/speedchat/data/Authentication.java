package com.example.weverson.speedchat.data;

import com.example.weverson.speedchat.domain.Authenticable;

import rx.Observable;

public interface Authentication {

    void createNewUser(Authenticable authenticable);

    Observable<Void> updateProfile(Authenticable authenticable);

}
