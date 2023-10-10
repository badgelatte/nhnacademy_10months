package com.nhnacademy;

import java.util.Queue;

public abstract class Port_prior {
    static int count;
    String id;
    String name;
    
    Queue<Message> queue;

    protected Port_prior() {
        this(String.valueOf(System.currentTimeMillis()) + (++count));
    }

    protected Port_prior(String id) {
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
