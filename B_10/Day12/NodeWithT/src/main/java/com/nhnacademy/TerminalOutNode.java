package com.nhnacademy;

public class TerminalOutNode extends OutputNode{
    public TerminalOutNode() {
        this(1);
    }

    // port 갯수 = count
    public TerminalOutNode(int count) {
        super(count);
    }

    void perform() {
        for (int i = 0; i < getInputPortCount(); i++) {
            if(getInputPort(i).hasMessage()) {
                Message message = getInputPort(i).poll();

                if(message instanceof StringMessage) {
                    System.out.println(((StringMessage)message).getMessage());
                }
                else if(message instanceof LongMessage) {
                    System.out.println(((LongMessage)message).getMessage());
                }
            }
        }
    }
}
