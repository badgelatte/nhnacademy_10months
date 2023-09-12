package com.nhnacademy.thread_class_extension;

public class Counter {
    String name;
    int count;
    int maxCount;

    public Counter(String name, int maxCount) {
        this.name = name;
        this.maxCount = maxCount;
        count = 0;
    }

    public void run() {
        while (count < maxCount) {
            try {
                ++count;
                System.out.println(name + " : " + count);
                Thread.sleep(1000); // -> main은 Thread이기 때문에 다른 애들처럼 implements나 extends하지 않는다. 
            } catch (InterruptedException ignore) {
                Thread.currentThread().interrupt();
            }
        }
    }
}