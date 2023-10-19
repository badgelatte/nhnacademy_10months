package com.nhnacademy.aiot.general;

import java.util.Iterator;
import java.util.NoSuchElementException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyStack implements Iterable {
    private int top;
    private final int[] arr;

    private final int capacity;

    public MyStack(int capacity) {
        if(capacity < 0) {
            throw new IllegalArgumentException();
        }
        top = -1;
        this.capacity = capacity;
        arr = new int[capacity];
    }

    public void push(int data) {
        // TODO 1.stack에 data를 넣습니다.
        if(top < capacity) {
            arr[++top] = data;
            log.info("data push : {}", data);
            log.info("top : {}", top);
        }
    }

    public int pop() {
        // TODO 2.데이터를 반환하고 반환된 데이터는 stack에서 제거됩니다.
        if(arr.length >= 0){
            log.info("data pop : {}", arr[top]);
            int data = arr[top];
            arr[top--] = 0;
            return data;
        }
        throw new NullPointerException();
    }

    @Override
    public Iterator iterator() {
        // TODO 3.Iterator를 구현합니다.
        return new Iterator<Integer>() {

            @Override
            public boolean hasNext() {
                if(top > -1){
                    log.info("top has data");
                    return true;
                }
                log.error("top doesn't has data");
                return false;
            }

            @Override
            public Integer next() {
                if(hasNext()) {
                    log.info("top data is {}", arr[top]);
                    return arr[top--];
                }
                log.error("There is no data");
                throw new NoSuchElementException();
            }
            
        };
    }

}