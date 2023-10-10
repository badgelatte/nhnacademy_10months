package com.nhnacademy.aiot;

import java.util.concurrent.Semaphore;

public class Fail {
    private final Semaphore s1 = new Semaphore(0);  // -> acquire가 1개도 없다
    private final Semaphore s2 = new Semaphore(1);
    private final int count;
    boolean runningfalse = false;

    public Fail(int count) {
        this.count = count;
    }

    public synchronized void printOne(Runnable runnable) throws InterruptedException {
        while (count > 0) {
            if(!s2.tryAcquire()){
                wait();
            }
            else {
                runnable.run();
                notifyAll();
                s2.release();
            }
            break;
        }
    }

    public synchronized void printTwo(Runnable runnable) throws InterruptedException {
        while (count > 0) {
            if(!s2.tryAcquire()){
                wait();
            }
            else {
                runnable.run();
                notifyAll();
                s2.release();
            }
            break;
        }
    }
}
