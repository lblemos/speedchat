package com.example.weverson.speedchat.login.domain.model;

import android.support.annotation.NonNull;

public class User implements Authenticable {

    @NonNull private String mNickname;

    @NonNull private String mEmail;

    @NonNull private String mPassword;


    public User(@NonNull String nickname) {
        setNickname(nickname);
    }

    public User(@NonNull String nickname, @NonNull String email, @NonNull String password){
        this(nickname);
        mEmail = email;
        mPassword = password;
    }

    @NonNull
    public String getNickname(){
        return mNickname;
    }

    @NonNull
    public User setNickname(@NonNull String nickname) {
        if(nickname == null) {
            throw new NullPointerException("the nickname can not be null");
        }
        mNickname = nickname;
        return this;
    }

    @NonNull
    @Override
    public String getEmail() {
        return mEmail;
    }

    @NonNull
    @Override
    public String getPassword() {
        return mPassword;
    }

    @Override
    public boolean isAuthentic(Authenticable authenticable) {
        return false;
//        return (getEmail().equals(authenticable.getEmail())) && (getPassword().equals(authenticable.getPassword()));
    }
}
