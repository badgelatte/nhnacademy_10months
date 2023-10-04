import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.zip.InflaterInputStream;

public class EchoServer extends Thread{
    // 1. 생성자 - 스레드 - 읽고쓰고 추가 - 스레드 도는동안 입력받은 거 client한테 message 전달
    // 2. main 
    Socket socket;
    public EchoServer(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        ) {

            while (!Thread.currentThread().isInterrupted()) {
                writer.write(reader.readLine());
                writer.flush();
            }
        } catch (IOException e) {
        }
        try {
            socket.close();
        } catch (IOException e) {
        }
    }

    public static void main(String[] args) {
        int port = 1234;

        try (
            ServerSocket serverSocket = new ServerSocket(port);
        ) {
            while(!Thread.currentThread().isInterrupted()) {
            Socket socket = serverSocket.accept();

            EchoServer server = new EchoServer(socket);
            server.start();

            
            }
        } catch (Exception e) {

        }

    }
}
