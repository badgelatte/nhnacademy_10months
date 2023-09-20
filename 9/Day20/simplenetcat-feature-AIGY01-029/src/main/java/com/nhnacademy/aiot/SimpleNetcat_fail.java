package com.nhnacademy.aiot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;

public class SimpleNetcat_fail {

    /* static class ServerMode{
        
        public void run() {
            try (ServerSocket serverSocket = new ServerSocket(portnum);
            Socket socket = serverSocket.accept();
            
            // 표준 입력(stdin)으로 입력된 데이터를 client로 전송한다. - server Mode
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            // Client가 접속하여 보내주는 데이터를 표준 출력(stdout)으로 출력한다. - serverM
            BufferedWriter stdout = new BufferedWriter(new OutputStreamWriter(System.out));
        ) {
                
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    static class ClientMode{

    } */

    // args - arguments의 약자로 입력 인자를 말한다.
    public static void main(String[] args) {
        // Client Mode
        
        // serversocket = port만 받는다
        // snc -l port
        String isServer = args[1];

        String port = args[2];
        int portnum = Integer.parseInt(port);

        // serversocket - portnum이라는 port 번호를 받아서 serversocket에서 연결 요청 받아줄때까지 대기
        ServerSocket serverSocket = new ServerSocket(portnum);
        // serverSocket.accept() -> 연결 요청을 받아줌
        // socket = server~~~ -> socket과 serverSocket을 연결함
        Socket socket = serverSocket.accept();
        
        BufferedReader stdin;
        BufferedWriter stdout;

        BufferedReader inClient;
        BufferedWriter outClient;

        // Server Mode
        if(isServer.equals("-l")) {
            try (
            // 표준 입력(stdin)으로 입력된 데이터를 client로 전송한다. - server Mode
            stdin = new BufferedReader(new InputStreamReader (System.in));
            // Client가 접속하여 보내주는 데이터를 표준 출력(stdout)으로 출력한다. - serverM
            stdout = new BufferedWriter(new OutputStreamWriter(System.out));
        ) {
            String line;
            while ((line = stdin.readLine()) != null) {
                outClient.write(line + "\n");
                outClient.flush();
            } catch (Exception e) {
                String stderr = "서버에 연결하지 못하였습니다.";
                System.out.println(stderr);
            }
        }

        // Client Mode
        else {
            String hostAddress = isServer;
            try (
                // 입력은 표준입력(stdin)으로 받아서 server에 전송한다. - Client Mode
                inClient = new BufferedReader(new InputStreamReader(System.in));
                // 출력은 표준출력(stdout)을 이용하고, server에서 받은 데이터를 출력한다. - ClientM
                outClient = new BufferedWriter(new OutputStreamWriter(System.out));
            ) {
                
            } catch (Exception e) {
                String stderr = "서버에 연결하지 못하였습니다.";
                System.out.println(stderr);
            }
        }
        // System.out.println(portnum);

        /* try (
            // tcp 연결
            ServerSocket serverSocket = new ServerSocket(portnum);
            Socket socket = serverSocket.accept();
            
            // 표준 입력(stdin)으로 입력된 데이터를 client로 전송한다. - server Mode
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            // Client가 접속하여 보내주는 데이터를 표준 출력(stdout)으로 출력한다. - serverM
            BufferedWriter stdout = new BufferedWriter(new OutputStreamWriter(System.out));
        
            // 입력은 표준입력(stdin)으로 받아서 server에 전송한다. - Client Mode
            BufferedReader inClient = new BufferedReader(stdin);

            // 출력은 표준출력(stdout)을 이용하고, server에서 받은 데이터를 출력한다. - ClientM
            BufferedWriter outClient = new BufferedWriter(stdout);
            ) {
                String line;
                while((line = socketIn.readLine())!= null){
                    socket.close(); 
                }
        } catch (Exception e) {
            String stderr = "서버에 연결하지 못하였습니다.";
            System.out.println(stderr);
        } */

    }
        
    }
    
}
