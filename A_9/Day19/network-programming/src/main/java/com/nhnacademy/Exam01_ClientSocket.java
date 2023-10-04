package com.nhnacademy;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Exam01_ClientSocket {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12346);

            System.out.println("서버에 연결되었습니다.");
            socket.close();
        } catch (UnknownHostException e) {
            // UnknownHostException || IOException - 앞에꺼 못하면 뒤에꺼까지한다.
            // if - else문과 같이
            // 앞에서 UnknownHostException을 처리 못하면 
            // 뒤에 IOException을 처리한다
            // 둘의 순서를 바꾸면 뭐라한다
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
