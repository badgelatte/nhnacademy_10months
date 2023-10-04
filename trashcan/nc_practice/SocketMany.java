import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SocketMany {
    
    public static void main(String[] args) {
        try (
            Socket socket = new Socket("localhost", 1232);
            BufferedReader terminalIn = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter terminalOut = new BufferedWriter(new OutputStreamWriter(System.out));
            BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter socketOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        ) {
            Transfer tIsO = new Transfer(terminalIn, socketOut);
            Transfer sItO = new Transfer(socketIn, terminalOut);

            tIsO.start();
            sItO.start();

            tIsO.join();
            sItO.join();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}