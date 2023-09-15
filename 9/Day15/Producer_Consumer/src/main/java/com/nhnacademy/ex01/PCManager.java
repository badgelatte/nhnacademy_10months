package com.nhnacademy.ex01;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

@Slf4j
public class PCManager {
    private final LinkedList<Integer> buffer = new LinkedList<>();
    
    private final int bufferSize;

    public PCManager(int bufferSize) {
        this.bufferSize = bufferSize;
    }
    public void produce() throws InterruptedException {
        int data = 0;

        //todo 생산자 구현
        
        // 무한 루프 돌게 해야한다 -> 그래야 계속 생산하니까
        while(true) {
            synchronized(this) {
                // buffer의 사이즈가 max 사이즈와 같으면 대기
                while(buffer.size() == bufferSize) {
                    wait();
                }
                // max 사이즈와 같지 않으면 대기에서 빠져나옴

                // buffer에 data 더하기
                buffer.add(++data);
                log.info(" 생산자 - 데이터 생성: {}", data);
                // 데이터를 넣었다고 알림
                notifyAll();

                Thread.sleep(500);
            }
        }
    }

    public void consume() throws InterruptedException {
        //todo 소비자 구현

        while(true) {
            synchronized(this) {
                while(buffer.size() == 0) {
                    wait();
                }

                // buffer의 데이터 소비하기
                int data = buffer.removeFirst();
                log.info(" 소비자-데이터 소비: {}", data);
                notifyAll();

                Thread.sleep(500);
            }
        }
    }
}