package com.nhnacademy.node;

import com.nhnacademy.message.LongMessage;

public class Ticker extends InputNode{
    long count = 0;
    boolean counterMode = false;

    // public Ticker(int count){
    //     super(count);
    // }

    public Ticker(int count, boolean counterMode) {
        super(count);
        this.counterMode = counterMode;
    }

    public Ticker() {
        this(1, false);
    }

    @Override
    void process() {
        if(counterMode) {
            // 시간에 맞춰서 수를 센다.
            output(new LongMessage(++count));
        }else {
            // 현재 시간을 출력한다.
            output(new LongMessage(System.currentTimeMillis()));
        }
    }
}
