package com.example.weverson.speedchat.domain.abstraction;

import android.support.annotation.NonNull;

public interface Authenticable {

    void setUid(@NonNull String uid);

    void setEmail(@NonNull String email);

    String getUid();

    String getEmail();

    String getPassword();

}
