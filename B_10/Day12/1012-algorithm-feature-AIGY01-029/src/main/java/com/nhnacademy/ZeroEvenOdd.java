package com.nhnacademy;

import java.util.function.IntConsumer;

public class ZeroEvenOdd {

    private int n;
    int count = 1;
    boolean runningflagEven = false;
    boolean runningflagOdd = false;
    // int currentNumber = 1;
    // boolean zeroPrinted = false;

    public ZeroEvenOdd(int n) {
        if(n < 0 || n > 100) {
            throw new IllegalArgumentException();
        }
        this.n = n;
    }

    public synchronized void zero(IntConsumer printNumber) throws InterruptedException {
        while (n >= count) {
            // !(runningflagEven ^ runningflagOdd)
            // if((!(runningflagEven && runningflagOdd)) || (!(runningflagEven && runningflagOdd))) {
            if(runningflagEven || runningflagOdd){
                wait();
            }
            else {  // runningflagEven나 runningflagOdd가 false라면 실행
                printNumber.accept(0);
                if(count % 2 == 1) {
                    runningflagEven =true;
                }
                else {
                    runningflagOdd = true;
                }
                notifyAll();
            }
        }
    }

    public synchronized void even(IntConsumer printNumber) throws InterruptedException {
        while (n >= count) {
            if(!runningflagEven) {  // false라면 wait
                wait();
            }
            else {   // true
                printNumber.accept(count++);
                runningflagEven = false;
                notifyAll();
            }
        }
    }

    public synchronized void odd(IntConsumer printNumber) throws InterruptedException {
        while (n >= count) {
            if(!runningflagOdd) {
                wait();
            }
            else {
                printNumber.accept(count++);
                runningflagOdd = false;
                notifyAll();
            }
        }
    }

}