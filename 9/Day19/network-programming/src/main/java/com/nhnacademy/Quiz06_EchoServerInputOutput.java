package com.nhnacademy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

// 보내고 받음
public class Quiz06_EchoServerInputOutput {
    public static void main(String[] args) {
        byte[] buffer = new byte[2048];

        // try-resources: try(){}catch(){} 형태로 사용하는데 try 다음의 ()는 선언하는것으로
        // AutoCloseable이 들어있는 class만 선언 및 사용할 수 있다.

        try ( BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); // 입력
            // socket 열기    
            Socket socket = new Socket("localhost", 12345);
            // 이 두줄(1, 2)을 합친게 두 줄 아랫것이다.
            //  InputStream inputStreram = socket.getOutputStream();    // 1
            //  BufferedOutputStream outputStream = new BufferedOutputStream(inputStreram); // 2
            // BufferedOutputStream outputStream = new BufferedOutputStream(socket.getOutputStream());

            // Buffer -> 한 줄씩 본다.
            // output 출력
            BufferedOutputStream outputStream = new BufferedOutputStream(socket.getOutputStream());
            // input 입력
            BufferedInputStream inputStream = new BufferedInputStream(socket.getInputStream());
            ){
            String line;

            // line 하나 읽기
            while((line = reader.readLine())!= null) {
                outputStream.write(line.getBytes());
                // getBytes : String에서 byte로 바꿈 why? write가 byte로 받기 때문
                outputStream.write('\n');

                // 하나가 무조건 나간다 => Buffer에 있는게 무조건 내려간다
                outputStream.flush();


               int length = inputStream.read(buffer);
               // 하나 죽고 하나 받고라는 뜻 = 보내면 나간다는 말
               System.out.println(new String(buffer, 0, length));
               

                // exit라 쓰면 종료하기
                if(line.equals("exit")) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
