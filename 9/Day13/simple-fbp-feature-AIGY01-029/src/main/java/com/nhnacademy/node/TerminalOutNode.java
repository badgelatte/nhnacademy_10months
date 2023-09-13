package com.nhnacademy.node;

import com.nhnacademy.message.LongMessage;
import com.nhnacademy.message.Message;
import com.nhnacademy.message.StringMessage;
import com.nhnacademy.port.Port;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// system out으로 message 받는데 super로 위에 거 다 가져오면 된다.
public class TerminalOutNode extends OutputNode {
    public TerminalOutNode() {
        // super(1);
        // super보단 this를 써서 저거 TerminalOutNode(int count) 하나만 신경쓰면 된다
        this(1);
    }

    public TerminalOutNode(int count) {
        super(count);

    }

    /* //시간 더 빨리 하기위해 작성
    @Override
    void preprocess(){
        setInterval(1);
    } */

    // inputnode를 다 검사할 거다
    //      -> message를 가지고 있다면 message를 꺼내온다.
    @Override
    public void process() {
        // port마다 스캔해서 찍어주기 
        // 메세지 있는가 확인 -> hasMessage() 사용
        // Message type 체크해서 
        for(int i = 0; i < getInputPortCount(); i++) {

            if(getInputPort(i).hasMessage()) {
                log.trace("Message : {}", i);

                // 해당 포트에서 message를 가져온다
                Message message = getInputPort(i).get();
                
                // message가 무슨 타입인가 확인
                if(message instanceof StringMessage){
    
                    // 확인했으면 가져와도 상관 X -> payload하도록
                    System.out.println(((StringMessage)message).getPayLoad());
                }
                // 만일 긴 메세지가 온게 맞다면
                else if (message instanceof LongMessage) {
                    // 출력하도록 한다.
                    System.out.println(((LongMessage)message).getPayload());
                }
            }

        }
    }
}