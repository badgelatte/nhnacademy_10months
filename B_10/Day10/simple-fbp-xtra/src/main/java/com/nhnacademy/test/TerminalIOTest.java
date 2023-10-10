package com.nhnacademy.test;

import com.nhnacademy.node.TerminalInNode;
import com.nhnacademy.node.TerminalOutNode;

public class TerminalIOTest {
    public static void main(String[] args) {
        TerminalInNode inNode = new TerminalInNode();
        TerminalOutNode outNode = new TerminalOutNode();

        inNode.connect(0, outNode.getInput(0));

        outNode.start();
        inNode.start();
    }
}
