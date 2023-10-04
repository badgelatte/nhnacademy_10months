package com.nhnacademy;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Node {
    static Integer count;
    final String id;
    Logger logger;

    protected Node() {
        this.id = String.format("%s-%02d", getClass().getSimpleName(), count);
        count++;
        logger.trace("create node : {}", id);
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