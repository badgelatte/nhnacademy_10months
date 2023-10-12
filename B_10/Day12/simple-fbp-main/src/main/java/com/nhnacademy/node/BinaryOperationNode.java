package com.nhnacademy.node;

public abstract class BinaryOperationNode extends InputOutputNode {
    BinaryOperationNode() {
        super(2, 1);
    }

    BinaryOperationNode(String name) {
        super(name, 2, 1);
    }
}
