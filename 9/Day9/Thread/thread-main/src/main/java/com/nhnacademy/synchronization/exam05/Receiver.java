package com.nhnacademy.synchronization.exam05;

import java.util.concurrent.ThreadLocalRandom;

public class Receiver implements Runnable {
    private Data load;

    public Receiver(Data load) {
        this.load = load;
    }

    public void run() {
        for (String receivedMessage = load.receive(); !"End".equals(receivedMessage); receivedMessage = load
                .receive()) {

            System.out.println(receivedMessage);

            // Thread.sleep() to mimic heavy server-side processing
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));  // 1에서 5초 사이 랜덤 값을 가져옴
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread Interrupted");
            }
        }
    }
}