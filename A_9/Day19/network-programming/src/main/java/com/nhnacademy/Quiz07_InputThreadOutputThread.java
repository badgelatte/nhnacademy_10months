package com.nhnacademy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Quiz07_InputThreadOutputThread {

    // 받아서 찍어주는 거 함 -> 내가 찍는 걸 thread로 만듦
    static class Receiver implements Runnable{  // 받는 쪽 - thread로 분리할거라 runnable 사용
        BufferedInputStream inputStream;

        public Receiver(BufferedInputStream inputStream) {  // BufferedInputStream으로 받겠다
            this.inputStream = inputStream;
        }

        //run이 필요하다
        @Override
        public void run() {
            try {
                int length;
                byte [] buffer = new byte[2048];
                //length가 inputStream에서 buffer받은게 0보다 크거나 같으면
                while((length = inputStream.read(buffer))>= 0) {
                    System.out.println(new String(buffer, 0, length));
                }
                
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    static class Sender implements Runnable{
        BufferedOutputStream outputStream;

        public Sender(BufferedOutputStream outputStream) {
            this.outputStream = outputStream;
        }

        @Override
        public void run() {
            try{
                String line;
    
                // line 하나 읽기
                while((line = outputStream.readLine())!= null) {
                    outputStream.write(line.getBytes());
                    outputStream.write('\n');
                }
            } catch(IOException e) {
                System.out.println(e);
            }
        }

    }

    // 전환이 되는 거지 동시에하는 게 아니다 -> thread가 전환이 빠름 & 일을 두가지로 쪼갬
    // thread로 받는 건 받는거고 주는건 주는거다


    public static void main(String[] args) {
        byte[] buffer = new byte[2048];

        try ( BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            // socket 열기    
            Socket socket = new Socket("ems.nhnacademy.com", 12345);
            //     
            BufferedOutputStream outputStream = new BufferedOutputStream(socket.getOutputStream());
            BufferedInputStream inputStream = new BufferedInputStream(socket.getInputStream())
            ){


            Receiver receiver = new Receiver(inputStream);

            Thread receiverThread = new Thread(receiver);

            //실행시키면 된다 -> 받을 준비 끝남
            receiverThread.start();

            String line;

            // line 하나 읽기
            while((line = reader.readLine())!= null) {
                outputStream.write(line.getBytes());
                outputStream.write('\n');

                // 하나가 무조건 나간다
                outputStream.flush();


               int length = inputStream.read(buffer);
               // 하나 죽고 하나 받고라는 뜻 = 보내면 나간다는 말
               System.out.println(new String(buffer, 0, length));
               

                // exit라 쓰면 종료하기
                /* if(line.equals("exit")) {
                    break;
                } */
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
