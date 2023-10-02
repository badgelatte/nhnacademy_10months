import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TI_TO_SI_SO_Thread {

    public static void main(String[] args) {
        
        try (
            Socket socket = new Socket("localhost", 1232);
            
            BufferedReader terminalIn = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter terminalOut = new BufferedWriter(new OutputStreamWriter(System.out));
            
            BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter socketOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            ) {
                while(true) {
                SI_TO thread = new SI_TO(socketIn, terminalOut);
                // TI_SO thread = new TI_SO(terminalIn, socketOut);
                // TI_SO.start(); // TI -> SO
                thread.start();  // SI -> TO
            }
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}

class TI_SO extends Thread{
    BufferedReader terminalIn;
    BufferedWriter socketOut;
    public TI_SO(BufferedReader terminalIn, BufferedWriter socketOut) {
        this.terminalIn = terminalIn;
        this.socketOut = socketOut;
    }
}

class SI_TO extends Thread{
    BufferedReader socketIn;
    BufferedWriter terminalOut;

    public SI_TO(BufferedReader socketIn, BufferedWriter terminalOut) {
        this.socketIn = socketIn;
        this.terminalOut = terminalOut;
    }

    public void run() {
        String line;
        try{
            while((line = socketIn.readLine())!= null) {
                terminalOut.write(line);
                terminalOut.flush();
            }
        } catch(IOException e) {
            System.out.println(e);
        }
    }
}
