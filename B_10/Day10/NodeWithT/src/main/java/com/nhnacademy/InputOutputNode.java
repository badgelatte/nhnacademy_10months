package com.nhnacademy;

public abstract class InputOutputNode extends ActiveNode {
    final Port [] ports;
    final Port [] peerPorts;

    protected InputOutputNode(int inputCount, int outputCount) {
        ports = new Port[inputCount];
        for (int i = 0; i < ports.length; i++) {
            ports[i] = new Port();  // ...? 인스턴스화할 수 없다?
        }
        peerPorts = new Port[outputCount];
    }

    public void connect(int index, Port port) {
        ports[index] = port;
    }

    public int getInputPortCount() {
        return ports.length;
    }

    public Port getInputPort(int index) {
        return ports[index];
    }

    void output(Message message) {
        for (Port port : peerPorts) {
            if(port != null){
                port.put(message);
            }
        }
    }
}
