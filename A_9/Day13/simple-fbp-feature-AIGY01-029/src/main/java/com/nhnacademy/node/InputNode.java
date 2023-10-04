package com.nhnacademy.node;

import com.nhnacademy.exception.AlreadyExistException;
import com.nhnacademy.exception.InvalidArgumentException;
import com.nhnacademy.exception.OutOfBoundsException;
import com.nhnacademy.message.Message;
import com.nhnacademy.port.Port;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InputNode extends ActiveNode{
    private final Port [] peerPorts;    // 상대방 PORT

    // public으로 만들지 않는다 -> 추상클래스라서
    // 생성할 때 몇개까지 끼워넣을 수 있는가
    protected InputNode(int count) {
        super();
        if (count <= 0) {
            throw new InvalidArgumentException();
        }
        // 상대방이 가진 것을 끼워 넣기 때문
        peerPorts = new Port[count];
    }

    // 연결해준다는 거
    // 꼽혀있는 거만 사용할 거다

    // 몇번째 port(연결점)에 연결할 건지 지정해주는 거
    public void connect(int index, Port port) {
        if(count <= index) {
            throw new OutOfBoundsException();
        }

        if(peerPorts[index] != null) {
            throw new AlreadyExistException();
        }
        peerPorts[index] = port;
    }

    // 메세지 출력
    void output(Message message) {
        // 연결된 것만 message를 보낸다.
        for(Port port : peerPorts) {
            if(port != null) {
                // 오류 잡기 위해 만듦
                // log.trace("input node : {}");
                port.put(message);
            }
        }
    }
}
