package com.nhnacademy;

// abstract 추상 - 기능 구현 X
public abstract class InputOutputNode extends ActiveNode {
    final Port [] ports;
    final Port [] peerPorts;

    protected InputOutputNode(int inputCount, int outputCount) {
        ports = new Port[inputCount];
        if(inputCount < 0) {
            System.out.println(inputCount + "개는 만들 수 없습니다");
        }
        for (int i = 0; i < ports.length; i++) {
            ports[i] = new Port();
        }
        peerPorts = new Port[outputCount];
    }

    public void connect(int index, Port port) {
        if(index < 0 || index > ports.length) {
            System.out.println("해당 포트는 없습니다.");
        }
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
