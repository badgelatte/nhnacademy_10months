package com.nhnacademy.snc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Transfer extends Thread {
    BufferedReader reader;
    BufferedWriter writer;

    public Transfer(BufferedReader reader, BufferedWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public void run() {
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                writer.write(line + "\n");
                writer.flush();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
