package com.nhnacademy.node;

import com.nhnacademy.message.LongMessage;

public class SubtractNode extends BinaryOperationNode {
    @Override
    void process() {
        if (((getInputWire(0) != null) && (getInputWire(0).hasMessage()))
                && ((getInputWire(1) != null) && (getInputWire(1).hasMessage()))) {

            long value = ((LongMessage) getInputWire(0).get()).getPayload()
                    - ((LongMessage) getInputWire(1).get()).getPayload();

            output(new LongMessage(value));
        }
    }
}
