package com.nhnacademy.ex01;
 

import java.lang.Thread;
import java.util.LinkedList;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PCManager_I_made extends Thread{
    private final LinkedList<Integer> buffer = new LinkedList<>();
    private final int bufferSize;
    Thread thread;

    public PCManager_I_made(int bufferSize) {
        this.bufferSize = bufferSize;
        thread = new Thread();
    }
    public void produce() throws InterruptedException {
        //todo 생산자 구현
        int data = 0;
        thread.start();

        // buffer가 가득차 있을때 활동하면 안된다.
        if(bufferSize == data) {
            thread.wait();
            // Thread.sleep(1000);
        }
        else {
            // Producer(생산자)는 데이터를 생성하고 이를 버퍼에 넣음
            buffer.add(data++);
            // data++;
            
            // 다음에 생산자가 데이터를 버퍼에 넣으면 잠자는 소비자가 깨어납니다.
            thread.notifyAll();
            System.out.println("데이터를 넣었습니다.");
        }
    }

    public void consume() throws InterruptedException {
        //todo 소비자 구현

        // 데이터가 buffer가 비어 있을때 활동하면 안된다.
        if(buffer.isEmpty()) {
            thread.wait();
        } else {
            if(buffer.size() == bufferSize){
                // 한 번에 한 조각씩 데이터를 소비
                buffer.removeFirst();
                System.out.println("데이터가 꽉 찼습니다.");
            } else {
                // Consumer(소비자)는 한 번에 한 조각씩 데이터를 소비합니다. 즉 버퍼에서 데이터를 소비 합니다.
                buffer.get(0);
                System.out.println(buffer.get(0));
                buffer.removeFirst();
                thread.notifyAll();
            }
        }

    }
}