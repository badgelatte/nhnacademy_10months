package com.nhnacademy.aiot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Snc {// made by 수석님


    public static void main(String[] args) {
        String host = "localhost";
        int port = 1234;
        
        // args는 항상2보다 컷다
        if(args.length < 2) {
            System.out.println("설정이 잘못되었습니다.");
        }
        
        if(!args[0].equals("-l")){
            // equals가 내용이 동등한가를 비교하기때문에 equals 사용해야한다(사용하는 거 추천)

            host = args[0];
            try{
                port = Integer.parseInt(args[1]);
            } catch(NumberFormatException e){
                System.err.println(e);
                System.exit(1);
            }
                try (ServerSocket serverSocket = new ServerSocket(port);
                Socket socket = serverSocket.accept();
                BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter socketOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader terminalIn = new BufferedReader(new InputStreamReader(System.in));   // -> 표준 입력에서 받는다
                BufferedWriter terminalOut = new BufferedWriter(new OutputStreamWriter(System.out));

            ) {
                String line;
                    
                    Transfer receiver1 = new Transfer(socketIn, terminalOut);   // socket에서 terminal로 나가는 거
                    Transfer receiver2 = new Transfer(terminalIn, socketOut);   // terminal에서 socket으로 나가는 거
                    
                    receiver1.start();
                    receiver2.start();
                    
                    receiver1.join();
                    receiver2.join();
            } catch (Exception e) {
                // TODO: handle exception
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
                    String line;
                    
                    Transfer receiver1 = new Transfer(socketIn, terminalOut);   // socket에서 terminal로 나가는 거
                    Transfer receiver2 = new Transfer(terminalIn, socketOut);   // terminal에서 socket으로 나가는 거
                    
                    receiver1.start();
                    receiver2.start();
                    
                    // receiver1,2이 끝날때까지 기다릴게
                    receiver1.join();
                    receiver2.join();
            
            /* String line;
            // 이런 식으로 while을 만들면 입력할때만 상태쪽에서 주는 것들이 출력된다.
            while((line = terminalIn.readLine()) != null){ // line = terminalIn.readLine() : 화면에서 들어오는 걸 읽는다.
                socketOut.write(line + "\n");
                
                // 화면으로 출력하는 걸 읽는다.
                line = socketIn.readLine();
                terminalOut.write(line + "\n");
            } */
            
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (IOException e) {
        }
    }
        
    }
}
