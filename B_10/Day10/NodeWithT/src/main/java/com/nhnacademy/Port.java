package com.nhnacademy;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import org.apache.logging.log4j.core.net.Priority;

// object를 선언한 시점에 정해진다
public abstract class Port {
    static int count;
    String id;
    Queue<Message> queue;
    // PriorityQueue -> 앞에 있는 거 먼저 처리

    public Port() {
        // this(String.valueOf(System.currentTimeMillis()) + (++count));
        queue = new LinkedList<Message>();
    }

    protected Port(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void put(Message message) {
        queue.add(message);
    }

    public Message peek() {
        return queue.peek();
        // peek - 보고 참조하고(사용하고) 지우진 않음
    }

    public Message poll() {
        return queue.poll();
        // poll - 데이터를 가져와서 없애줌
    }
}
