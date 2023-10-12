package com.nhnacademy.test;

import com.nhnacademy.node.TerminalOutNode;
import com.nhnacademy.node.TimerNode;
import com.nhnacademy.wire.Wire;
import com.nhnacademy.wire.BufferedWire;

public class TickerTest {
    public static void main(String[] args) {
        TimerNode ticker = new TimerNode(TimerNode.Mode.COUNT);
        TerminalOutNode outNode = new TerminalOutNode();

        Wire wire = new BufferedWire();

        ticker.connectOutputWire(0, wire);
        outNode.connectInputWire(0, wire);

        outNode.start();
        ticker.start();
    }
}
