package com.nhnacademy;

public class StringMessage extends Message{
    String message;
    public StringMessage() {
        this("");
    }

    public StringMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
