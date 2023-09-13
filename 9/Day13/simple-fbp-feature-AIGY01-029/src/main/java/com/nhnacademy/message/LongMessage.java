package com.nhnacademy.message;

public class LongMessage extends Message{
    long payload;

    // 긴 메세지를 저장한다.
    public LongMessage(long payload) {
        this.payload = payload;
    }

    // 가지고 있는 값을 얻고 싶다
    public long getPayload() {
        return payload;
    }

}
