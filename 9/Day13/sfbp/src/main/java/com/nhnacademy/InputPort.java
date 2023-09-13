package com.nhnacademy;

import java.util.LinkedList;
import java.util.Queue;

public class InputPort {
    Queue<Message> messageQueue;

    public InputPort(Node node) {
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
