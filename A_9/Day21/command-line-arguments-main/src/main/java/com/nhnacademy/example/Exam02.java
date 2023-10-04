package com.nhnacademy.example;

public class Exam02 {
    public static void main(String[] args) {
        for (String arg : args) {
            try {
                int value = Integer.parseInt(arg);
                System.out.println("int : " + value);
            } catch (NumberFormatException ignore) {
                System.out.println("String : " + arg);
            }
        }
    }
}
