package com.example.weverson.speedchat.data.repository;

import com.example.weverson.speedchat.domain.abstraction.Authenticable;

import java.util.List;

import rx.Observable;

public interface UserRepository {

    Observable<Void> createNewUser(Authenticable authenticable);

    Observable<Void> SignIn(Authenticable authenticable);

    Authenticable getCurrentUser(Authenticable authenticable);

    Observable<List<String>> getUserChannels(Authenticable authenticable);

}
