package com.nhnacademy.mart.threadlocal;

import com.nhnacademy.mart.thread.Channel;
import com.nhnacademy.mart.thread.Client;
import com.nhnacademy.mart.thread.WorkerPool;

public class MainTest {
    public static void main(String[] args) {
        // 소비자 세팅 끝
        // Channel channel = new Channel(20);

        // WorkerPool workerPool = new WorkerPool( 3, channel);
        // workerPool.start();

        // new Thread(new Client(channel)).start();
        // new Thread(new Client(channel)).start();
        // new Thread(new Client(channel)).start();

        SharedUserContext user1 = new SharedUserContext(1);
        SharedUserContext user2 = new SharedUserContext(2);
        // 출력했을때 private static final로 만들면 userContext를 만들면 1과 2의 내용물이 같을까?
        // Session을 이용해서 공유하기 때문에 내용물이 바뀔 수 있는 것이다.

        new Thread(user1).start();
        new Thread(user2).start();
        
    }
}
