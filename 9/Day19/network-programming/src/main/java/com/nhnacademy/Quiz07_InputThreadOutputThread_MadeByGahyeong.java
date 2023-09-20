package com.nhnacademy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

//Quiz-07. Echo server에 연결하여 문자열을 보내고 받도록 구성
//받는것과 상관없이 보낼 수 있다. //비동기 통신
//sender추가
public class Quiz07_InputThreadOutputThread_MadeByGahyeong {

    static class Receiver implements Runnable{

        BufferedInputStream inputStream;

        public Receiver(BufferedInputStream inputStream){
            this.inputStream = inputStream;
        }

    @Override
        public void run(){ //input은 외부의 길이를 읽어와서 스트링으로 출력을 해준다.
            try {
                byte [] buffer = new byte[2048];
                int length; 
                while((length = inputStream.read(buffer)) >= 0){ //버퍼를 읽어와야하니 버퍼를 만든다.
                    System.out.println(new String(buffer, 0, length)); //정보를 가지고 있다가 스트링으로 읽어서 찍는다. offset:시작점
                }
                
            } catch (IOException e) {
                System.out.println("Receiver Error : " + e.getMessage());
                 e.getStackTrace();
            }
        }
    }

    static class Sender implements Runnable{ //output은 라인을 읽고 작성해서 외부로 보낸다.
        BufferedOutputStream outputStream;
        BufferedReader reader;

        public Sender(BufferedOutputStream outputStream, BufferedReader reader){
            this.outputStream = outputStream;
            this.reader = reader;
        }

        @Override
        public void run(){
            try {
                String line;
                while((line = reader.readLine()) != null){
                outputStream.write(line.getBytes());
                outputStream.write('\n');
                outputStream.flush(); //버퍼가 채워지면 한번에 보냄.
                }
            } catch (IOException e) {
                System.out.println("Sender Error : " + e);
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        byte [] buffer = new byte[2048];

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            //기본 연결 설정
            Socket socket = new Socket("localhost", 1234);
            OutputStream output = socket.getOutputStream();
            InputStream input = socket.getInputStream();
            BufferedOutputStream outputStream = new BufferedOutputStream(output);
            BufferedInputStream inputStream = new BufferedInputStream(input)){ //try 끝
            // BufferedInputStream - byte 타입으로 받는다
            // BufferedInputStream inputStream2 = new BufferedInputStream(socket.getInputStream());

            //Output, input을 받는 스레드를 하나 만듦. input을 데이터를 가지고 있다가 run하면 무조건 입력하도록
            Receiver receiver = new Receiver(inputStream); 
            Sender sender = new Sender(outputStream, reader);

            Thread receiverThread = new Thread(receiver);
            Thread senderThread = new Thread(sender);
            receiverThread.start(); // 받을 준비가 끝남!
            senderThread.start();
        
            while(true){
                Thread.sleep(1000);
            }
        } catch (IOException e) {
            System.out.println("Main Error : " + e);
        }

        
    }
}