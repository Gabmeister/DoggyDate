package com.example.doggydateapp;

class Messages {

    String message;
    int image;

    public Messages(String message, int image) {
        this.message = message;
        this.image = image;
    }

    public String getMessage() {
        return message;
    }

    public int getImage() {
        return image;
    }
}