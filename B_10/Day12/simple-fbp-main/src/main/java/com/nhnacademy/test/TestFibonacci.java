package com.nhnacademy.test;

import com.nhnacademy.message.LongMessage;
import com.nhnacademy.node.AddNode;
import com.nhnacademy.node.LatchNode;
import com.nhnacademy.node.ReplicationNode;
import com.nhnacademy.node.TerminalOutNode;
import com.nhnacademy.node.TimerNode;
import com.nhnacademy.wire.BufferedWire;
import com.nhnacademy.wire.Wire;

public class TestFibonacci {
    public static void main(String[] args) {
        TimerNode clockNode = new TimerNode("clock", TimerNode.Mode.CLOCK);
        LatchNode latchNode1 = new LatchNode("latch1", new LongMessage(1));
        LatchNode latchNode2 = new LatchNode("latch2", new LongMessage(0));
        ReplicationNode replicationNode1 = new ReplicationNode("rep1", 2);
        ReplicationNode replicationNode2 = new ReplicationNode("rep2", 2);
        ReplicationNode replicationNode3 = new ReplicationNode("rep3", 2);
        AddNode addNode1 = new AddNode("add1");
        TerminalOutNode standardOutNode = new TerminalOutNode("out1");

        Wire wire1 = new BufferedWire();
        Wire wire2 = new BufferedWire();
        Wire wire3 = new BufferedWire();
        Wire wire4 = new BufferedWire();
        Wire wire5 = new BufferedWire();
        Wire wire6 = new BufferedWire();
        Wire wire7 = new BufferedWire();
        Wire wire8 = new BufferedWire();
        Wire wire9 = new BufferedWire();
        Wire wire10 = new BufferedWire();

        clockNode.connectOutputWire(0, wire1);
        clockNode.setInterval(1000);
        replicationNode1.connectInputWire(0, wire1);
        replicationNode1.connectOutputWire(0, wire2);
        replicationNode1.connectOutputWire(1, wire3);
        latchNode1.connectInputWire(0, wire2);
        latchNode1.connectInputWire(1, wire10);
        latchNode1.connectOutputWire(0, wire4);
        replicationNode2.connectInputWire(0, wire4);
        replicationNode2.connectOutputWire(0, wire5);
        replicationNode2.connectOutputWire(1, wire6);
        latchNode2.connectInputWire(0, wire3);
        latchNode2.connectInputWire(1, wire5);
        latchNode2.connectOutputWire(0, wire7);
        replicationNode3.connectInputWire(0, wire7);
        replicationNode3.connectOutputWire(0, wire8);
        replicationNode3.connectOutputWire(1, wire9);
        addNode1.connectInputWire(0, wire9);
        addNode1.connectInputWire(1, wire6);
        addNode1.connectOutputWire(0, wire10);
        standardOutNode.connectInputWire(0, wire8);

        standardOutNode.start();
        replicationNode1.start();
        replicationNode2.start();
        replicationNode3.start();
        addNode1.start();
        latchNode2.start();
        latchNode1.start();
        clockNode.start();
    }
}
