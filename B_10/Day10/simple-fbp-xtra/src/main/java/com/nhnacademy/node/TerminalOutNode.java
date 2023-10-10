package com.nhnacademy.node;

import com.nhnacademy.message.LongMessage;
import com.nhnacademy.message.Message;
import com.nhnacademy.message.StringMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TerminalOutNode extends OutputNode {
    public TerminalOutNode() {
        this(1);
    }

    public TerminalOutNode(int count) {
        super(count);
    }

    @Override
    void preprocess() {
        setInterval(1);
    }

    @Override
    void process() {
        for (int i = 0; i < getInputCount(); i++) {
            if (getInput(i).hasMessage()) {
                log.trace("Message : {}", i);

                Message message = getInput(i).get();

                if (message instanceof StringMessage) {
                    System.out.println(((StringMessage) message).getPayload());
                } else if (message instanceof LongMessage) {
                    System.out.println(((LongMessage) message).getPayload());
                }

            }
        }
    }
}
