package com.nhnacademy.node;

import com.nhnacademy.message.LongMessage;

public class Ticker extends InputNode {
    long count = 0;
    boolean counterMode = false;

    public Ticker(int count, boolean counterMode) {
        super(count);
        this.counterMode = counterMode;
    }

    public Ticker() {
        this(1, false);
    }

    @Override
    void process() {
        if (counterMode) {
            output(new LongMessage(++count));
        } else {
            output(new LongMessage(System.currentTimeMillis()));
        }
    }
}
