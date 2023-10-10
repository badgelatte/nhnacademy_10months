package com.nhnacademy;

public abstract class InputNode extends ActiveNode {
    final Port [] peerPorts;

    InputNode(int count) {
        peerPorts = new Port[count];
    }

    public void connect(int index, Port port) {
        peerPorts[index] = port;
    }

    void output(Message message) {
        for (Port port : peerPorts) {
            if(port != null) {
                port.put(message);
            }
        }
    }
}
