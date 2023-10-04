package com.nhnacademy.ex01;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ex01Main {
    public static void main(String[] args) {
        PCManager pcManager = new PCManager(2);
    
        Thread t1 = new Thread(new Runnable() {
           @Override
           public void run() {
            try {
                
                pcManager.produce();
            } catch (InterruptedException e) {
                log.error("interruptedException", e);
            }
           } 
        });
    
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pcManager.consume();
                } catch (InterruptedException e) {
                    log.error("InterruptedException", e);
                }
            }
        });

        t1.start();
        t2.start();
        
    }
}
