import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer2 extends Thread{
    Socket socket;
    
    
    public EchoServer2(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        ) {
            while(!Thread.currentThread().isInterrupted()) {
                writer.write(reader.readLine() + "\n");
                writer.flush();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            socket.close();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        try (
            ServerSocket serversc = new ServerSocket(1232);
            Socket socket = serversc.accept();
        ) {
            EchoServer2 server = new EchoServer2(socket);

            server.start();
            server.join();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
