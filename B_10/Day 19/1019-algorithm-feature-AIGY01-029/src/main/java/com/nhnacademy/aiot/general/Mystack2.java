package com.nhnacademy.aiot.general;

    
import java.util.Iterator;
import java.util.NoSuchElementException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Mystack2 implements Iterable {
    private int top;
    private final int[] arr;

    private final int capacity;

    public Mystack2(int capacity) {
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
        }
    }

    public int pop() {
        // TODO 2.데이터를 반환하고 반환된 데이터는 stack에서 제거됩니다.
        if(arr.length >= 0){
            return arr[top--];
        }
        throw new NullPointerException();
    }

    @Override
    public Iterator iterator() {
        // TODO 3.Iterator를 구현합니다.
        return new Iterator<Integer>() {

            @Override
            public boolean hasNext() {
                return top > -1;
            }

            @Override
            public Integer next() {
                if(hasNext()) {
                    return arr[top--];
                }
                throw new NoSuchElementException();
            }
            
        };
    }

}