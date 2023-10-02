package client_broadcasting;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;


public class Trans extends Thread{
    BufferedReader reader;
    BufferedWriter writer;

    public Trans (BufferedReader reader, BufferedWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                writer.write(reader.readLine() +" \n");
                writer.flush();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
