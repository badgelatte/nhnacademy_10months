package com.nhnacademy;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Ex01 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);

            System.out.println("서버에 연결되었습니다.");

            socket.close();
        } catch (UnknownHostException e) {
            System.out.println(e);
        } catch (IOException e) {
            e.printStackTrace();
            // System.out.println(e);
        }
    }
}
