import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Transfer extends Thread{
    BufferedReader reader;
    BufferedWriter writer;

    public Transfer (BufferedReader reader, BufferedWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public void run() {
        String line;
        try{
            while(!Thread.currentThread().isInterrupted()) {
                line = reader.readLine();
                writer.write(line + "\n");
                writer.flush();
            }

        } catch(IOException e) {
            System.out.println(e);
            System.out.println("서버에 연결되지 않습니다.");
        }
    }
}
