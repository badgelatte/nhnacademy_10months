package com.nhnacademy.node;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Node {
    static int count;
    final String id;

    protected Node() {
        count++;
        this.id = String.format("%s-%02d", getClass().getSimpleName(), count);
        // logback.xml 속 level과 맞춰줘야한다.
        log.trace("create node : {}", id);
    }

    public String getId() {
        return id;
    }

    public abstract String getName();

    public abstract void setName(String name);

    public static int getCount() {
        return count;
    }

}