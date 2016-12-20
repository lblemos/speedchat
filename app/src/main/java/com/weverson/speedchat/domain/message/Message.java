package com.weverson.speedchat.domain.message;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    private String sender;
    private String name;
    private String message;
    private long timestamp;

    public Message(){
        timestamp = System.currentTimeMillis();
    }

    public Message(String name, String message) {
        this();
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public Message setName(String name) {
        this.name = name;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Message setMessage(String message) {
        this.message = message;
        return this;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Message setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getCreatedAt() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(new Date(timestamp));
    }

    public String getSender() {
        return sender;
    }

    public Message setSender(String sender) {
        this.sender = sender;
        return this;
    }
}
