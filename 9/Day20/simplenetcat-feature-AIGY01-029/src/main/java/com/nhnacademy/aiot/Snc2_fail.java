package com.nhnacademy.aiot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Snc2_fail {// made by 수석님 - 따라가다 실패

    public void run(Socket socket) {
        try (
                BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter socketOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader terminalIn = new BufferedReader(new InputStreamReader(System.in));   
                BufferedWriter terminalOut = new BufferedWriter(new OutputStreamWriter(System.out));
                ) {
                    Transfer receiver1 = new Transfer(socketIn, terminalOut);   // socket에서 terminal로 나가는 거
                    Transfer receiver2 = new Transfer(terminalIn, socketOut);   // terminal에서 socket으로 나가는 거
                    
                    receiver1.start();
                    receiver2.start();
                    
                    receiver1.join();
                    receiver2.join();
        }catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (IOException e) {
        }
    }
    
    public static void main(String[] args) {
        String host = "localhost";
        int port = 1234;
        boolean serverMode = true;
        
        // args는 항상2보다 컷다
        if(args.length < 2) {
            System.out.println("설정이 잘못되었습니다.");
        }
        
        if(!args[0].equals("-l")){
            // equals가 내용이 동등한가를 비교하기때문에 equals 사용해야한다(사용하는 거 추천)

            host = args[0];
            serverMode = false;
            /* try{
                port = Integer.parseInt(args[1]);
            } catch(NumberFormatException e){
                System.err.println(e);
                System.exit(1);
            } */
        }
        if(serverMode) {
            try (
            //     ServerSocket serverSocket = new ServerSocket(port);
            // Socket socket = serverSocket.accept();
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            ) {
                // Snc2.run(socket);
            
            } catch (IOException e) {
            }
         } else{
            host = args[0];
            try{
                port = Integer.parseInt(args[1]);
                //만일 args[1]가 int 형태가 아니었다면 오류가 나니까
            } catch(NumberFormatException e){
                System.err.println(e);
                System.exit(1);
            }
            
            try (
                Socket socket = new Socket(host, port);
                // byte 하나씩 보낸다(하위 레벨) -> 그래서 보다 상위 레벨을 사용하겠다고 선언한다
                BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter socketOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader terminalIn = new BufferedReader(new InputStreamReader(System.in));   // -> 표준 입력에서 받는다
                BufferedWriter terminalOut = new BufferedWriter(new OutputStreamWriter(System.out));    // 화면 출력을 위해서 작성 but 이거 굳이 해줄 필욘 X, 딴거로도 작성 가능
                
                ) {
                    
            
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (IOException e) {
        }
    }
        
    }
}
