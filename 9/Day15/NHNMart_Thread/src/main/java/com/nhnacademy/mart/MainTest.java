package com.nhnacademy.mart;

import com.nhnacademy.mart.thread.Channel;
import com.nhnacademy.mart.thread.Client;
import com.nhnacademy.mart.thread.WorkerPool;

public class MainTest {
    public static void main(String[] args) {
        // 소비자 세팅 끝
        Channel channel = new Channel(20);

        WorkerPool workerPool = new WorkerPool( 3, channel);
        workerPool.start();

        new Thread(new Client(channel)).start();
        new Thread(new Client(channel)).start();
        new Thread(new Client(channel)).start();
        
    }
}
