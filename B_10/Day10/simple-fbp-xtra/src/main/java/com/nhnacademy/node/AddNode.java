package com.nhnacademy.node;

import com.nhnacademy.message.LongMessage;
import com.nhnacademy.message.Message;

public class AddNode extends InputOutputNode {
    public AddNode(int inCount, int outCount) {
        super(inCount, outCount);
    }

    @Override
    void process() {
        boolean accept = true;

        for (int i = 0; i < getInputPortCount(); i++) {
            accept = accept && getInputPort(i).hasMessage();
        }

        if (accept) {
            long sum = 0;

            for (int i = 0; i < getInputPortCount(); i++) {
                Message message = getInputPort(i).get();
                if (message instanceof LongMessage) {
                    sum += ((LongMessage) message).getPayload();
                }
            }

            output(new LongMessage(sum));
        }
    }
}
