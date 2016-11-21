package com.example.weverson.speedchat.domain.user;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.example.weverson.speedchat.domain.Authenticable;

public class User implements Authenticable{

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

    @Override
    @NonNull
    public String getNickname(){
        return mNickname;
    }

    @Override
    public Uri getPhotoUri() {
        return Uri.parse("http://google.com.br");
    }

    @NonNull
    public User setNickname(@NonNull String nickname) {
        if(nickname == null) {
            throw new NullPointerException("the nickname can not be null");
        }
        mNickname = nickname;
        return this;
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
