package com.nhnacademy.test;

import com.nhnacademy.node.AddNode;
import com.nhnacademy.node.TerminalOutNode;
import com.nhnacademy.node.TimerNode;
import com.nhnacademy.wire.BufferedWire;
import com.nhnacademy.wire.Wire;

public class AddTest {
    public static void main(String[] args) {
        TimerNode ticker1 = new TimerNode(TimerNode.Mode.COUNT);
        TimerNode ticker2 = new TimerNode(TimerNode.Mode.COUNT);
        AddNode adder = new AddNode();
        TerminalOutNode out = new TerminalOutNode();

        Wire wire1 = new BufferedWire();
        Wire wire2 = new BufferedWire();
        Wire wire3 = new BufferedWire();

        ticker1.connectOutputWire(0, wire1);
        ticker2.connectOutputWire(0, wire2);
        adder.connectInputWire(0, wire1);
        adder.connectInputWire(1, wire2);
        adder.connectOutputWire(0, wire3);
        out.connectInputWire(0, wire3);

        out.start();
        adder.start();
        ticker1.start();
        ticker2.start();
    }
}
