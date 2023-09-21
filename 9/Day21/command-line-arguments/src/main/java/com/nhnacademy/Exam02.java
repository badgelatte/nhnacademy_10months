package com.nhnacademy;

public class Exam02{
    public static void main(String[] args) {
        for (String arg : args) {
            try{
                // float value = Float.parseFloat(arg);    // exception으로 numberformatException을 throw해야한다.
                // parseFloat 대신 valueOf 쓸 수도있다
                // float value2 = Float.valueOf(arg);
                System.out.println("Float : " + Float.parseFloat(arg));
            } catch (NumberFormatException ignore) {
                System.out.println("String : " + arg);
            }
        }
    }
}