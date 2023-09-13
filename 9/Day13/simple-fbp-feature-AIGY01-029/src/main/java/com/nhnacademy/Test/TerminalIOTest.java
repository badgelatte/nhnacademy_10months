package com.nhnacademy.Test;

import com.nhnacademy.node.TerminalInNode;
import com.nhnacademy.node.TerminalOutNode;

public class TerminalIOTest {
    public static void main(String[] args) {
        TerminalInNode inNode = new TerminalInNode();
        TerminalOutNode outNode = new TerminalOutNode();

        // input node 0번째와 output node 0번째를 연결
        inNode.connect(0, outNode.getInputPort(0));

        outNode.start();
        inNode.start();
    }
}
