package com.nhnacademy.args;

public class CgiMain {
    public static void main(String[] args) {
        System.out.println("Content-type: text/plain");
        System.out.println();

        System.getProperties().forEach((k,v) -> {
            System.out.println(k + "=" + v);
        });
    }
}
