package com.nhnacademy.test;

import com.nhnacademy.node.TerminalInNode;
import com.nhnacademy.node.TerminalOutNode;
import com.nhnacademy.wire.BufferedWire;
import com.nhnacademy.wire.Wire;

public class TerminalIOTest {
    public static void main(String[] args) {
        TerminalInNode inNode = new TerminalInNode();
        TerminalOutNode outNode = new TerminalOutNode();

        Wire wire = new BufferedWire();

        inNode.connectOutputWire(0, wire);
        outNode.connectInputWire(0, wire);

        outNode.start();
        inNode.start();
    }
}
