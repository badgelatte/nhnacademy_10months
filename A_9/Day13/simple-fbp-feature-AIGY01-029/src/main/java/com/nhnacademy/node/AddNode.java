package com.nhnacademy.node;

import com.nhnacademy.message.LongMessage;
import com.nhnacademy.message.Message;

public class AddNode extends InputOutputNode {
    public AddNode() {
        // node 2개받아서 출력 1개 = BinaryOperator
        super(2, 1);
    }

    // node 여러개 받아서 출력 1개
    public AddNode(int inCount, int outCount) {
        super(inCount, outCount);
    }

    @Override
    // port 2개가 값이 있을때 , 모든 포트가 값이 있을때
    void process () {
        boolean accept = true;

        for (int i = 0; i < getInputPortCount(); i++) {
            // 모두 메세지를 가지고 있을 경우 
            accept = accept && getInputPort(i).hasMessage();
        }

        if(accept) {
            long sum = 0;
            for (int i = 0; i < getInputPortCount(); i++) {
            // 모두 메세지를 가지고 있을 경우 2 메세지를 합친다
                Message message = getInputPort(i).get();
                if(message instanceof LongMessage) {
                    sum += ((LongMessage)message).getPayload();
                }
            }

            output(new LongMessage(sum));
        }
    }
}
