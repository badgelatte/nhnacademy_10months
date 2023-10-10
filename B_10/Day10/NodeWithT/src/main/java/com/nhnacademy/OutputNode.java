package com.nhnacademy;

import com.nhnacademy.Port;

public abstract class OutputNode extends ActiveNode {
    Port [] ports;

    OutputNode(int count) {
        ports = new Port[count];
        for (int i = 0; i < ports.length; i++) {
            ports[i] = new Port();
        }
    }

    public int getInputPortCount() {
        return ports.length;
    }

    public Port getInputPort(int index) {
        return ports[index];
    }
}