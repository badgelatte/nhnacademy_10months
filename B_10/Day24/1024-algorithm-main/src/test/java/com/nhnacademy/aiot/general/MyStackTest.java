package com.nhnacademy.aiot.general;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class MyStackTest {

  private MyStack<String> stack;

  @BeforeEach
  void setUp(){
    stack = new MyStack(10);
  }

  @Test
  @Order(0)
  @DisplayName("initial - capacity : capacity < 1")
  void stack_initial_capacity(){
    Assertions.assertThrows(IllegalArgumentException.class,()->{
      new MyStack<String>(0);
    });
  }

  @Test
  @Order(1)
  @DisplayName("stack-push")
  void push() {
    stack.push("nhnacademy");
    Assertions.assertEquals("nhnacademy",stack.peek());;
  }

  @Test
  @Order(2)
  @DisplayName("emptystack-pop")
  void empty_stack_pop() {
    Assertions.assertThrows(EmptyStackException.class,()->{
      stack.pop();
    });
  }

  @Test
  @Order(3)
  @DisplayName("stack-pop")
  void pop(){
    stack.push("nhnacademy");
    Assertions.assertEquals("nhnacademy", stack.pop());
  }

  @Test
  @Order(4)
  @DisplayName("emptyStack-peek")
  void empty_stack_peek() {
    Assertions.assertThrows(EmptyStackException.class,()->{
      stack.peek();
    });
  }

  @Test
  @Order(5)
  @DisplayName("stack-search-notfound")
  void search_notfound(){
    stack.push("nhnacademy");
    stack.push("google");
    stack.push("daum");

    Assertions.assertEquals(stack.search("kakao"),-1);
  }

  @Test
  @Order(6)
  @DisplayName("stack-search")
  void search() {
    stack.push("nhnacademy");
    stack.push("google");
    stack.push("daum");

    Assertions.assertEquals(stack.search("nhnacademy"),0);

  }

  @Test
  @Order(7)
  @DisplayName("stack-empty")
  void empty() {
    Assertions.assertEquals(true,stack.empty());
  }

  @Test
  @Order(8)
  @DisplayName("stack-not-empty")
  void not_empty() {
    stack.push("nhnacademy");
    Assertions.assertEquals(false,stack.empty());
  }

  @Test
  @Order(9)
  @DisplayName("iterator")
  void iterator() {
    stack.push("A");
    stack.push("B");
    stack.push("C");
    StringBuilder sb = new StringBuilder();
    Iterator<String> iterator = stack.iterator();
    while(iterator.hasNext()){
      sb.append(iterator.next());
    }
    Assertions.assertEquals("ABC", sb.toString());
  }

  @Test
  @Order(10)
  @DisplayName("iterator no search element")
  void iterator_no_search_element(){
    Assertions.assertThrows(NoSuchElementException.class,()->{
      stack.iterator().next();
    });
  }


}