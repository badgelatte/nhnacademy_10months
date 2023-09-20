package com.nhnacademy.aiot;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleNetcat_Thread {
    static class Sender implements Runnable {
        BufferedReader terminalIn;
        BufferedWriter socketOut;

        public Sender(BufferedReader terminalIn, BufferedWriter socketOut) {  // BufferedInputStream으로 받겠다
            this.terminalIn = terminalIn;
            this.socketOut = socketOut;
        }
        @Override
        public void run() {
            try{
                String line;
                while((line = terminalIn.readLine())!= null) {
                    socketOut.write(line + "\n");
                    socketOut.flush();
                }


            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    // 내가 server한테 보내는거
    static class Receiver implements Runnable {
        BufferedReader socketIn;
        BufferedWriter terminalOut;

        public Receiver( BufferedReader socketIn, BufferedWriter terminalOut) {  
            this.socketIn = socketIn;
            this.terminalOut = terminalOut;
        }

        @Override
        public void run() {
            try{
                String line;
                
                while ((line = socketIn.readLine())!= null) {
                    terminalOut.write(line + "\n");
                    terminalOut.flush();
                }

            }catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String hostname = args[1];

        String port = args[2];
        int portnum = Integer.parseInt(port);

        /* BufferedReader st = new BufferedReader(new InputStreamReader(System.in));
        String l = st.readLine();

        StringTokenizer str = new StringTokenizer(l);
        String option = str.nextToken();
        String hostname = str.nextToken();
        String port = str.nextToken();
        int portnum = Integer.parseInt(port); */

        // if(snc == "snc") {}
        if(hostname.equals("-l")) {
            //servermode
            try (
                ServerSocket serverSocket = new ServerSocket(portnum);
                Socket socket = serverSocket.accept();

                BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                // BufferedWriter, BufferedReader - char 타입으로 받는다
                BufferedWriter socketOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader terminalIn = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter terminalOut = new BufferedWriter(new OutputStreamWriter(System.out));
            ) {
                Receiver receiver = new Receiver(socketIn, terminalOut);
                Thread receivThread = new Thread(receiver);

                Sender sender = new Sender(terminalIn, socketOut);
                Thread senderThread = new Thread(sender);

                receivThread.start();
                senderThread.start();
                receivThread.join();
                senderThread.join();

            } catch (IOException e) {
                String stderr = "서버에 연결하지 못하였습니다.";
                System.out.println(stderr);
            }
        }
        else {
            try (
                Socket socket = new Socket(hostname,portnum);

                BufferedReader socketIn2 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter socketOut2 = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader terminalIn2 = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter terminalOut2 = new BufferedWriter(new OutputStreamWriter(System.out));
            ) {
                Receiver receiver = new Receiver(socketIn2, terminalOut2);
                Thread receivThread = new Thread(receiver);

                Sender sender = new Sender(terminalIn2, socketOut2);
                Thread senderThread = new Thread(sender);

                receivThread.start();
                senderThread.start();
                receivThread.join();
                senderThread.join();

                // while((line = socketIn2.readLine()) != null) {}
            } catch (IOException e) {
                String stderr = "서버에 연결하지 못하였습니다.";
                System.out.println(stderr);
            }
        }
    }
}
