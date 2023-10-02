package client_broadcasting;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class client extends Thread{
    
    public static void main(String[] args) {
        int port = 1232;
        String host = "localhost";

        try {
            Socket socket = new Socket(host, port);
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter stdOut = new BufferedWriter(new OutputStreamWriter(System.out));
            BufferedReader soIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter soOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        
            Trans clientstdIn = new Trans(stdIn, soOut);
            Trans clientstdOut = new Trans(soIn, stdOut);

            clientstdIn.start();
            clientstdOut.start();
            
            clientstdIn.join();
            clientstdOut.join();

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("연결되지 않습니다.");
        }
    }
}