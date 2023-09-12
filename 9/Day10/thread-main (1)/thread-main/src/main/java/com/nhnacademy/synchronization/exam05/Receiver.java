package com.nhnacademy.synchronization.exam05;

import java.util.concurrent.ThreadLocalRandom;

public class Receiver implements Runnable {
    private Data load;

    public Receiver(Data load) {
        this.load = load;
    }

    public void run() {
        for (String receivedMessage = load.receive(); !"End".equals(receivedMessage); receivedMessage = load.receive()) {
            // 시작 : 받은 데이터를 receivedMessage에 넣는다; receivedMessage 중 "End"라는 글자와 같다면 종료 ; 반복문 : load에서 받은 것을 receivedMessage에 넣는다 
            System.out.println(receivedMessage);

            // Thread.sleep() to mimic heavy server-side processing
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));  // 1초에서 5초 사이 랜덤하게 sleep한다.
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread Interrupted");
            }
        }
    }
}