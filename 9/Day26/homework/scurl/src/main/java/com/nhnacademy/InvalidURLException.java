package com.nhnacademy;

public class InvalidURLException extends RuntimeException{
    public InvalidURLException() {
        super();
    }

    public InvalidURLException(String message) {
        // message = new String();
        System.out.println(message);
    }
}
