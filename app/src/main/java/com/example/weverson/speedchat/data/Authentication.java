package com.example.weverson.speedchat.data;

import com.example.weverson.speedchat.domain.Authenticable;
import com.example.weverson.speedchat.domain.user.User;

import rx.Observable;

public interface Authentication {

    Observable<Void> createNewUser(Authenticable authenticable);

    Observable<User> updateProfile(Authenticable authenticable);

}
