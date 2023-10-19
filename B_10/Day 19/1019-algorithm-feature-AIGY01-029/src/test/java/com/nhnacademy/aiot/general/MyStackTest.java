package com.nhnacademy.aiot.general;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class MyStackTest {
    MyStack myStack;
    
    @BeforeEach
    void setUp() {
        myStack = new MyStack(10);
    }

    @Test
    @DisplayName("capacity : -1")
    void constructor_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           new MyStack(-1);
        });
    }

    @Test
    @DisplayName("push, pop test")
    void push_pop_test () {
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        myStack.push(5);

        Assertions.assertEquals(myStack.pop(),5);
        Assertions.assertEquals(myStack.pop(),4);
        Assertions.assertEquals(myStack.pop(),3);
        Assertions.assertEquals(myStack.pop(),2);
        Assertions.assertEquals(myStack.pop(),1);
    }

    @Test
    @DisplayName("iterator test")
    void iterator_test() {
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        myStack.push(5);

        StringBuffer buffer = new StringBuffer();
        Iterator iterator = myStack.iterator();
        while(iterator.hasNext()){
            Integer data = (Integer) iterator.next();
            buffer.append(data);
            log.info("data:{}",data);
        }
        assertEquals("54321",buffer.toString());
    }
}
