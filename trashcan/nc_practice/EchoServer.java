import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) {
        try (
            ServerSocket server = new ServerSocket(1232);
            Socket socket = server.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        ) {
            String line;
            while((line = reader.readLine())!= null) {
                writer.write(line+"\n");
                writer.flush();
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
