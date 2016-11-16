package com.example.weverson.speedchat.login.domain.model;

public interface Authenticable {

    String getEmail();

    String getPassword();

    boolean isAuthentic(Authenticable authenticable);

}
