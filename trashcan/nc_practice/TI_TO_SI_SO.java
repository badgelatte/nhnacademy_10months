import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TI_TO_SI_SO {
    public static void main(String[] args) {
        try(
            Socket socket = new Socket("localhost", 1232);
        ){
            BufferedReader terminalIn = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter terminalOut = new BufferedWriter(new OutputStreamWriter(System.out));
            BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter socketOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String line;

            while (true) {
                if((line = terminalIn.readLine()) != null){
                    socketOut.write(line + "\n");
                    socketOut.flush();
                }
                if((line = socketIn.readLine())!= null) {
                    terminalOut.write(line + "\n");
                    terminalOut.flush();
                }
            }
            
        } catch(Exception e) {
            System.out.println(e);
        } 
    }
}
