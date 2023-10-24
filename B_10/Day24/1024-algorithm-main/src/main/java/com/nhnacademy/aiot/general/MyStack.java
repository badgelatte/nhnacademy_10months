package com.nhnacademy.aiot.general;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyStack<E> implements IStack<E> , Iterable<E> {

  public MyStack(int capacity){
  }

@Override
public Iterator<E> iterator() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'iterator'");
}

@Override
public void push(E item) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'push'");
}

@Override
public E pop() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'pop'");
}

@Override
public E peek() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'peek'");
}

@Override
public int search(Object o) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'search'");
}

@Override
public boolean empty() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'empty'");
}

}
