package com.nhnacademy;

import java.util.function.IntConsumer;

public class ZeroEvenOdd2 {
    // Fail
    private int n;
    static boolean zeroPrinted = false;
    int currentNumber = 1;
    
    public ZeroEvenOdd2(int n) {
        if(n < 0 || n > 100) {
            throw new IllegalArgumentException();
        }
        this.n = n;
    }

    public synchronized void zero(IntConsumer printNumber) throws InterruptedException {
        while (n >= currentNumber) {
            // !(runningflagEven ^ runningflagOdd)
            // if((!(runningflagEven && runningflagOdd)) || (!(runningflagEven && runningflagOdd))) {
            if(zeroPrinted){
                wait();
            }
            else {      // false
                printNumber.accept(0);
                zeroPrinted = true;
                notifyAll();
            }
        }
    }

    public synchronized void even(IntConsumer printNumber) throws InterruptedException {
        while (n >= currentNumber) {
            if(!zeroPrinted) {  // false라면 wait
                wait();
            }
            else if(currentNumber % 2 == 1) {   // true
                printNumber.accept(currentNumber++);
                zeroPrinted = false;
                notifyAll();
            }
        }
    }

    public synchronized void odd(IntConsumer printNumber) throws InterruptedException {
        while (n >= currentNumber) {
            if(!zeroPrinted) {
                wait();
            }
            else if(currentNumber % 2 == 0) {
                printNumber.accept(currentNumber++);
                zeroPrinted = false;
                notifyAll();
            }
            break;
        }
    }

}