package com.nhnacademy.mart.thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Channel {  // 채널을 통해 request를 공유함
    private final Queue<Request> queue;
    
    // queue가 무한정 돌아다닐 수 없으니 수를 지정하는 거
    private final int queueMaxSize;

    // 채널을 만듦
    public Channel(int ququeSize){
        this.queueMaxSize = ququeSize;
        queue = new LinkedList<>();
        
        // LinkedBlockingQueue를 이렇게 쓸 수도 있다
        // queue = new LinkedBlockingQueue<>(ququeSize);
    }

    // 요청을 queue에 넣는다.
    public synchronized Request takeRequeset() {
        return queue.poll();
    }

    public void addRequest(Request requeset) {
        while(queue.size() >= queueMaxSize){
            try {
                wait();
            } catch (InterruptedException e) {
                log.error("add queue", e);
            }
            queue.add(requeset);
            notifyAll();
        }
    }
}
