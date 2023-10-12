package com.nhnacademy.node;

public class ReplicationNode extends InputOutputNode {
    public ReplicationNode(int count) {
        super(1, count);
    }

    public ReplicationNode(String name, int count) {
        super(name, 1, count);
    }

    @Override
    void process() {
        if ((getInputWire(0) != null) && getInputWire(0).hasMessage()) {
            output(getInputWire(0).get());
        }
    }
}
