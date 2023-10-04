package com.nhnacademy.message;

public class Message {  // data만 포장하는 용도
    static int count;
    final String id;
    final long creationTime;

    public Message() {
        count++;
        id = String.format("%s-%02d".getClass().getSimpleName(), count);
        creationTime = System.currentTimeMillis();
    }

    // 필요한 getter와 setter 적기

    public String getId() {
        return id;
    }
    public long getCreationTime() {
        return creationTime;
    }
    
    public static int getCount() {
        return count;
    }
}
