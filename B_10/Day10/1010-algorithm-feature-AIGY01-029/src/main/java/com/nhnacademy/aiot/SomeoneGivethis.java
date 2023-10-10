package com.nhnacademy.aiot;

import java.util.concurrent.Semaphore;

public class SomeoneGivethis {
    private final Semaphore s1 = new Semaphore(0);
    private final Semaphore s2 = new Semaphore(1);  // permit 카드(or 키)가 현재 1개 있다.
    private int count;

    public SomeoneGivethis(int count) {
        this.count = count;
    }

    // 동시 진행함 -> printOne, printTwo

    public void printOne(Runnable runnable) throws InterruptedException {
        for(int i = 0; i < count; i++) {
            s2.acquire();   // s2의 permit 카드(or 키)를 들고 감
            System.out.print(1);
            s1.release();   // s1에 permit 카드(or 키)를 줌
        }
    }

    public void printTwo(Runnable runnable) throws InterruptedException {
        for(int i = 0; i < count; i++) {
            // s1이 permit 받을때까지 대기 => 첨에 Semaphore가 0이므로 
            // printOne이 s1.release할 때까지 대기
            s1.acquire();
            System.out.print(2);

            s2.release();
        }
    }
}