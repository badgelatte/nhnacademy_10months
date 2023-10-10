package com.nhnacademy.test;

import com.nhnacademy.node.TerminalOutNode;
import com.nhnacademy.node.Ticker;

public class TickerTest {
    public static void main(String[] args) {
        Ticker ticker = new Ticker(1, true);
        TerminalOutNode outNode = new TerminalOutNode();

        ticker.connect(0, outNode.getInput(0));

        outNode.start();
        ticker.start();
    }
}
