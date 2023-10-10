package com.nhnacademy.port;

import java.util.LinkedList;
import java.util.Queue;

import com.nhnacademy.message.Message;

public class Port {
    Queue<Message> messageQueue;

    public Port() {
        messageQueue = new LinkedList<>();
    }

    public void put(Message message) {
        messageQueue.add(message);
    }

    public boolean hasMessage() {
        return !messageQueue.isEmpty();
    }

    public Message get() {
        return messageQueue.poll();
    }
}
