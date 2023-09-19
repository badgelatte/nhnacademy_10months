package com.nhnacademy.practice;

import java.net.Socket;

public class TryWithResources2 {
    // try_catch문 ()안에 넣어서 사용하기
    public static void main(String[] args) {
        for(int port = 0; port < 65536;port++) {
            try (Socket socket = new Socket("localhost", port)){
                System.out.println(port + "가 열려있습니다.");
            } catch (Exception e) {
            }
        }
    }
}
