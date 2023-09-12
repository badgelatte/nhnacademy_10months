package com.nhnacademy.thread_class_extension;

public class Quiz01 {
    public static void main(String[] args) {    
        // main이라는 이름의 특수한 Thread가 돌아간다 -> 그래서 이후에 main이라는 Thread 밑에 thread1, 2가 돌아간 것이다
        Counter counter = new Counter("counter", 10);

        counter.run();
    }
}
