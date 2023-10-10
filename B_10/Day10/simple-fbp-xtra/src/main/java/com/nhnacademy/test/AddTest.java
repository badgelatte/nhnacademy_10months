package com.nhnacademy.test;

import com.nhnacademy.node.AddNode;
import com.nhnacademy.node.TerminalOutNode;
import com.nhnacademy.node.Ticker;

public class AddTest {
    public static void main(String[] args) {
        Ticker ticker1 = new Ticker(1, true);
        Ticker ticker2 = new Ticker(1, true);
        AddNode adder = new AddNode(2, 1);
        TerminalOutNode out = new TerminalOutNode();

        adder.connect(0, out.getInput(0));
        ticker1.connect(0, adder.getInputPort(0));
        ticker2.connect(0, adder.getInputPort(1));

        out.start();
        adder.start();
        ticker1.start();
        ticker2.start();
    }
}
