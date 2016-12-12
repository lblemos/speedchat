package com.example.weverson.speedchat.domain.channel;

import android.support.annotation.NonNull;


public class Channel {

    private String mTitle;

    private String mDescription;

    public Channel() {
    }

    public Channel(@NonNull String title, @NonNull String description) {
        mTitle = title;
        mDescription = description;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }
}
