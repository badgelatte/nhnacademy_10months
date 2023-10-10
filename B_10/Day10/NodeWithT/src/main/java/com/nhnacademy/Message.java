package com.nhnacademy;

public class Message {
    static int count;
    String id;
    String name;

    protected Message() {
        this(String.valueOf(System.currentTimeMillis() + (++count)));
    }

    protected Message(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
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
