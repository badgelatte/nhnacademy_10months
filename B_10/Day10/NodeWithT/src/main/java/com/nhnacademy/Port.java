package com.nhnacademy;

public abstract class Port {
    static int count;
    String id;
    String name;

    protected Port() {
        this(String.valueOf(System.currentTimeMillis()) + (++count));
    }

    protected Port(String id) {
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
