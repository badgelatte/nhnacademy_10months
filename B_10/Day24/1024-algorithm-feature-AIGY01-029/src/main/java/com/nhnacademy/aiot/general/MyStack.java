package com.nhnacademy.aiot.general;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class MyStack<E> implements IStack<E> , Iterable<E> {
  private int capacity;
  private List<E> list;
  private int top;

  public MyStack(int capacity){
    if(capacity < 1) {
        throw new IllegalArgumentException();
    }
    this.capacity = capacity;
    list = new ArrayList<>();
    top = -1;
  }

@Override
public Iterator<E> iterator() {
    return new Iterator<E>() {
        int now = 0;
        @Override
        public boolean hasNext() {
            return now <= top;
        }

        @Override
        public E next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            return list.get(now++);
        }
        
    };
}

@Override
public void push(E item) {
    if(top <= capacity) {
        list.add(item);
        top++;
    }
}

@Override
public E pop() {
    if(list.isEmpty()) {
        throw new EmptyStackException();
    }
    E topNum = list.get(top);
    list.remove(top--);
    return topNum;
}

@Override
public E peek() {
    if(list.isEmpty()) {
        throw new EmptyStackException();
    }
    return list.get(top);
}

@Override
public int search(E o) {
    // list의 indexOf 안에 indexOfRange 속 for문이 list의 index번째에 있는 것과 o를 하나씩 비교해준다
    // => list 모두를 꺼내서 o와 비교하는 것이 아니라 list 속에서 하나씩 꺼내서 o와 비교한다
    return list.indexOf(o);
}

@Override
public boolean empty() {
    return list.isEmpty();
}

}
