package client;
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
        String line;
        try {
            while ((line= reader.readLine())!= null) {
                writer.write(line +" \n");
                writer.flush();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
