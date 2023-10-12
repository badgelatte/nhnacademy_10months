package com.nhnacademy.test;

import com.nhnacademy.node.AddNode;
import com.nhnacademy.node.MultiplyNode;
import com.nhnacademy.node.SubtractNode;
import com.nhnacademy.node.TerminalOutNode;
import com.nhnacademy.node.TimerNode;
import com.nhnacademy.wire.BufferedWire;
import com.nhnacademy.wire.Wire;

public class TestArithmeticOperations {
    public static void main(String[] args) {
        TimerNode v1 = new TimerNode(TimerNode.Mode.COUNT);
        TimerNode v2 = new TimerNode(TimerNode.Mode.COUNT);
        TimerNode v3 = new TimerNode(TimerNode.Mode.COUNT);
        TimerNode v4 = new TimerNode(TimerNode.Mode.COUNT);
        AddNode addNode = new AddNode();
        SubtractNode subtractNode = new SubtractNode();
        MultiplyNode multiplyNode = new MultiplyNode();
        TerminalOutNode outNode = new TerminalOutNode();

        Wire wire1 = new BufferedWire();
        Wire wire2 = new BufferedWire();
        Wire wire3 = new BufferedWire();
        Wire wire4 = new BufferedWire();
        Wire wire5 = new BufferedWire();
        Wire wire6 = new BufferedWire();
        Wire wire7 = new BufferedWire();

        v1.connectOutputWire(0, wire1);
        v2.connectOutputWire(0, wire2);
        addNode.connectInputWire(0, wire1);
        addNode.connectInputWire(1, wire2);
        v3.connectOutputWire(0, wire3);
        v4.connectOutputWire(0, wire4);
        subtractNode.connectInputWire(0, wire3);
        subtractNode.connectInputWire(1, wire4);

        addNode.connectOutputWire(0, wire5);
        subtractNode.connectOutputWire(0, wire6);
        multiplyNode.connectInputWire(0, wire5);
        multiplyNode.connectInputWire(1, wire6);
        multiplyNode.connectOutputWire(0, wire7);
        outNode.connectInputWire(0, wire7);

        outNode.start();
        addNode.start();
        subtractNode.start();
        multiplyNode.start();
        v4.start();
        v3.start();
        v2.start();
        v1.start();
    }
}