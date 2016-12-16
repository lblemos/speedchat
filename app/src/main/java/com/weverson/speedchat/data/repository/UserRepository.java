package com.weverson.speedchat.data.repository;

import com.weverson.speedchat.domain.abstraction.Authenticable;

import rx.Observable;

public interface UserRepository {

    Observable<Void> createNewUser(Authenticable authenticable);

    Observable<Void> SignIn(Authenticable authenticable);

    Observable<Authenticable> fetchCurrentUser(Authenticable authenticable);

}