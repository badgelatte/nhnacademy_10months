import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
// socketIn에서 terminalOut으로 빠져나간다.
public class socketIn_terminalOut {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1235);
            BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter terminalOut = new BufferedWriter(new OutputStreamWriter(System.out));
            while (true) {
                terminalOut.write(socketIn.readLine()+ "\n");
                terminalOut.flush();
            }

            
        } catch (Exception e) {
            System.out.println("잘못 연결함");
        }
    }
}
