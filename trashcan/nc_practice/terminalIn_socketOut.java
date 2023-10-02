//terminalIn해서 nc로 연결해서 socketout으로 나와라

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class terminalIn_socketOut {
    
    public static void main(String[] args) {
        int port = 1235;
        
        try {
            Socket socket = new Socket("localhost",port);
            BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            while (true) {
                writer.write(read.readLine()+"\n");
                writer.flush();
                
            }

            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
}
