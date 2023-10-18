package com.nhnacademy;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class ZeroEvenOdd3 {
    private final Semaphore s1 = new Semaphore(0);
    private final Semaphore s2 = new Semaphore(0);
    private final Semaphore z1 = new Semaphore(1);

    private int n;
    int count = 1;

    // notifyAll(); aqier 안되면 저절로 안넘어가므로 wait도 필요없다.
    public ZeroEvenOdd3(int n){
        this.n = n*2;
        
        if((n < 1) || (n > 100)){
            throw new IllegalArgumentException();
        }
    }

    public void zero(IntConsumer printNumber) throws InterruptedException {
        // for (int i = 0; i < n; i++) {}
        while(count < n){
            if(z1.tryAcquire()) {
                printNumber.accept(0);
                count++;
                if(count % 2 == 0){
                    s1.release(); 
                }else {
                    s2.release();
                }
            }
            

        }

    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        //짝수
        // for (int i = 2; i <= maxEven; i += 2) {}
        
        while(count < n){
            s1.acquire();
            printNumber.accept(count);

            count++;
            z1.release();
        }
        
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        //홀수
        // for (int i = 1; i <= n; i += 2) {}
        
        while(count < n){
            s2.acquire(); // -1 가능
            printNumber.accept(count);

            count++;
            z1.release();
        }

    }



}