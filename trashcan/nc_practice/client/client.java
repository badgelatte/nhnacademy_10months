package client;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class client {
    public static void run(Socket socket){
        try{
            BufferedReader terminalIn = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter terminalOut = new BufferedWriter(new OutputStreamWriter(System.out));
            BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter socketOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        
            Trans clientstdIn = new Trans(terminalIn, socketOut);
            Trans clientstdOut = new Trans(socketIn, terminalOut);
        
            clientstdIn.start();
            clientstdOut.start();
            
            clientstdIn.join();
            clientstdOut.join();
        } catch (Exception e) {
                System.out.println(e);
                System.out.println("연결되지 않습니다.");
        }
    }
    public static void main(String[] args) {
        int port = 1232;
        String host = "localhost";

        // 메세지 전송, 메세지 받기
        try {
            Socket socket = new Socket(host, port);
            client.run(socket);
            
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("연결되지 않습니다.");
        }
    }
}