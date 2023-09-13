package com.nhnacademy.message;

public class StringMessage extends Message{ // message를 받아서 만든다
    String payload;

    public StringMessage(String payload) {
        this.payload = payload;
    }

    public String getPayLoad() {
        return payload;
    }
    
}
