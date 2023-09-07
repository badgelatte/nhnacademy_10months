package com.nhnacademy.app;
import java.util.Random;

public class JavaRandom {
    public static void main(String[] args) {
        Random r = new Random();
        int num = r.nextInt(100) + 1;
        
        System.out.println("java random:" + num);
        
    }
}
