package com.example.weverson.speedchat.data;

import com.example.weverson.speedchat.domain.Authenticable;

import rx.Observable;

public interface Authentication {

    Observable<Void> createNewUser(Authenticable authenticable);

}
