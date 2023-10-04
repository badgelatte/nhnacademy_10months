package com.nhnacademy.mart.mart.thread;

import java.util.LinkedList;
import java.util.Queue;

import com.nhnacademy.mart.thread.Request;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShoppingChannel {
    private final Queue<Request> queue;
    private final int MAX_QUEUE_SIZE = 10;

    public ShoppingChannel() {
        queue = new LinkedList<>();
    }
    public synchronized void addRequest(Request request) {
        while(queue.size() < MAX_QUEUE_SIZE) {
            try {
                wait();
            } catch (InterruptedException e) {
                log.error("add requst wait", e);
            }
        }
        queue.add(request);
        notifyAll();
    }

    public synchronized Request getRequest() throws InterruptedException{
        // queue가 비어있다면 대기상태
        while (queue.isEmpty()) {
            wait();
        }
        // 그렇지 않으면 queue를 꺼내서 처리한다.
        Request request = queue.poll();
        notifyAll();
        return request;
    }

}
