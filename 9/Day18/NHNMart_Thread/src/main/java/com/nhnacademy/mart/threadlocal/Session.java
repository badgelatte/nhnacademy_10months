package com.nhnacademy.mart.threadlocal;

public class Session {
    private final String userName;

    public Session(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
