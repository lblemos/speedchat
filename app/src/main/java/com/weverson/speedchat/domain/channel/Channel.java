package com.weverson.speedchat.domain.channel;

import android.support.annotation.NonNull;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class Channel implements Serializable {

    private String id;

    private String name;

    private boolean isPrivate;

    private String lastMessage;

    private String image;

    private String admin;

    private long timestamp;

    public Channel() {

    }

    public Channel(@NonNull String name) {
        setName(name);
        setId(name);
        timestamp = System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

    public Channel setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Channel setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public Channel setLastMessage(String description) {
        lastMessage = description;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Channel setImage(String image) {
        this.image = image;
        return this;
    }

    public String getAdmin() {
        return admin;
    }

    public Channel setAdmin(String admin) {
        this.admin = admin;
        return this;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Channel setPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
        return this;
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
