package com.weverson.speedchat.domain.channel;

import android.support.annotation.NonNull;

import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class Channel {

    private String mName;

    private String mlastMessage;

    private String mImage;

    private String mAdmin;

    private long mTimestamp;

    public Channel() {

    }

    public Channel(@NonNull String name) {
        mName = name;
        mTimestamp = System.currentTimeMillis();
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getLastMessage() {
        return mlastMessage;
    }

    public void setLastMessage(String description) {
        mlastMessage = description;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public String getAdmin() {
        return mAdmin;
    }

    public void setAdmin(String admin) {
        mAdmin = admin;
    }

    public long getTimestamp() {
        return mTimestamp;
    }
}
