package com.nhnacademy;

public class LongMessage extends Message{
    long message;

    public LongMessage() {
        this(1);
    }

    public LongMessage(long message) {
        this.message = message;
    }

    public long getMessage() {
        return message;
    }
}
