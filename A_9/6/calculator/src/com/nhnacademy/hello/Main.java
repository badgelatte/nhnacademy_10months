package com.nhnacademy.hello;

import com.nhnacademy.hello.util.Calculator;

public class Main {

  public static void main(String[] args) {
    System.out.println("Hello java!");

    System.out.println("더하기:" + Calculator.plus(20,10));
    System.out.println("빼기:" + Calculator.subtract(20,10));
    System.out.println("나누기:" + Calculator.divide(20,10));
    System.out.println("곱하기:" + Calculator.multiply(20,10));
  }
}