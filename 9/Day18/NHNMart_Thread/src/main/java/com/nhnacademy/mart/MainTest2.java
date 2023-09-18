package com.nhnacademy.mart;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.nhnacademy.mart.mart.thread.ShoppingChannel;
import com.nhnacademy.mart.mart.thread.ShoppingWorker;
import com.nhnacademy.mart.thread.Channel;
import com.nhnacademy.mart.thread.Client;
import com.nhnacademy.mart.thread.WorkerPool;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainTest2 {
    public static void main(String[] args) {
        ShoppingChannel shoppingChannel = new ShoppingChannel();
        // 한번에 10명씩 들어간다고 줬지만 10명보다 적을 수 있다
        // Pool에 10명 꽉 차 있음 대기해야한다
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i = 0; i< 10; i++) {
            executorService.execute(new ShoppingWorker(shoppingChannel));
        }


        // 소비자 세팅 끝
        Channel channel = new Channel(20);

        WorkerPool workerPool = new WorkerPool( 3, channel);
        workerPool.start();
        log.info("finished");

        new Thread(new Client(channel,shoppingChannel)).start();
        new Thread(new Client(channel,shoppingChannel)).start();
        new Thread(new Client(channel, shoppingChannel)).start();
        
    }
}
