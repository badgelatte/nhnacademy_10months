package com.nhnacademy.node;

import java.security.InvalidAlgorithmParameterException;

import com.nhnacademy.port.Port;

import com.nhnacademy.exception.InvalidArgumentException;
import com.nhnacademy.exception.OutOfBoundsException;


public class OutputNode extends ActiveNode {
    final Port [] ports;    // [] = array

    OutputNode(int count) {
        super();
        if(count <= 0) {
            throw new InvalidArgumentException();
        }
        ports = new Port[count];

        // inputPort를 가져올때 outputNode에 바로 끼워 넣어라
        // => 생성될때 port를 가진다.
        for(int i =0; i< count; i++) {
            ports[i] = new Port();
        }
        this.count = count;
    }

    public int getInputPortCount() {
        return ports.length;
    }

    public Port getInputPort(int index) {
        if(index < 0 || ports.length <= index) {
            throw new OutOfBoundsException();
        }
        return ports[index];
    }
}
