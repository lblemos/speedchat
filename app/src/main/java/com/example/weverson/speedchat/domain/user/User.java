package com.example.weverson.speedchat.domain.user;

import android.support.annotation.NonNull;

import com.example.weverson.speedchat.domain.abstraction.Authenticable;

public class User implements Authenticable{

    @NonNull private String mEmail;

    @NonNull private String mPassword;

    public User(@NonNull String email, @NonNull String password){
        mEmail = email;
        mPassword = password;
    }

    @Override
    @NonNull
    public String getEmail() {
        return mEmail;
    }

    @Override
    @NonNull
    public String getPassword() {
        return mPassword;
    }


}
