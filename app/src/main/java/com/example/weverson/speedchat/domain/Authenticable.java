package com.example.weverson.speedchat.domain;

import android.net.Uri;

public interface Authenticable {

    String getNickname();

    Uri getPhotoUri();

    String getEmail();

    String getPassword();

}
