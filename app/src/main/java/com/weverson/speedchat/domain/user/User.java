package com.weverson.speedchat.domain.user;

import android.support.annotation.NonNull;

import com.weverson.speedchat.domain.abstraction.Authenticable;

public class User implements Authenticable{

    @NonNull private String mUid;

    @NonNull private String mEmail;

    @NonNull private String mPassword;

    public User() {
    }

    public User(@NonNull String email, @NonNull String password){
        mEmail = email;
        mPassword = password;
    }

    @Override
    public void setUid(@NonNull String uid) {
        mUid = uid;
    }

    @Override
    public void setEmail(@NonNull String email) {
        mEmail = email;
    }

    @Override
    public String getUid() {
        return mUid;
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
