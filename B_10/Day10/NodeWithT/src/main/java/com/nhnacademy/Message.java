package com.nhnacademy;

public class Message {
    static int count;
    String id;
    String name;
    long creationTime;

    protected Message() {
        this(String.valueOf(System.currentTimeMillis() + (++count)));
    }

    protected Message(String id) {
        this.id = id;
        this.creationTime = System.nanoTime();
    }

    public String getId() {
        return id;
    }
    
    public long getCreationTime() {
        return creationTime;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public static int getTotalCount() {
        return count;
    }
}
