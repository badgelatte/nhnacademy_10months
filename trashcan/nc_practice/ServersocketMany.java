import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServersocketMany {
    public static void main(String[] args) {
        try (
            ServerSocket serverSocket = new ServerSocket(1232);
            Socket socket = serverSocket.accept();
            BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter socketOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            ) {
                Transfer sIsO = new Transfer(socketIn, socketOut);

                sIsO.start();

                sIsO.join();

        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
