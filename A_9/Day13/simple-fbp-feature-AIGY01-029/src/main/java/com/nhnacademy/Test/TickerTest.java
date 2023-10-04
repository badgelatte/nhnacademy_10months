package com.nhnacademy.Test;

import com.nhnacademy.node.TerminalOutNode;
import com.nhnacademy.node.Ticker;

public class TickerTest {
    public static void main(String[] args) {
        // 둘의 쓰임새의 차이를 보고 싶으면 Ticker를 봐라
        // Ticker ticker = new Ticker();
        // TerminalOutNode outNode = new TerminalOutNode();

        Ticker ticker = new Ticker(1, true);
        TerminalOutNode outNode = new TerminalOutNode();
        
        ticker.connect(0, outNode.getInputPort(0));

        outNode.start();
        ticker.start();
    }
}
