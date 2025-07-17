package com.example.chat;
public class MessageModel {
    private String text;
    private boolean isSent; // true = sent by me, false = received

    public MessageModel(String text, boolean isSent) {
        this.text = text;
        this.isSent = isSent;
    }

    public String getText() {
        return text;
    }

    public boolean isSent() {
        return isSent;
    }
}
