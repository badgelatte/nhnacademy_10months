package com.nhnacademy.node;

import com.nhnacademy.message.LongMessage;

public class DivideNode extends BinaryOperationNode {
    @Override
    void process() {
        if (((getInputWire(0) != null) && (getInputWire(0).hasMessage()))
                && ((getInputWire(1) != null) && (getInputWire(1).hasMessage()))) {

            long value1 = ((LongMessage) getInputWire(0).get()).getPayload();
            long value2 = ((LongMessage) getInputWire(1).get()).getPayload();

            if (value2 != 0) {
                output(new LongMessage(value1 / value2));
            }
        }
    }
}
