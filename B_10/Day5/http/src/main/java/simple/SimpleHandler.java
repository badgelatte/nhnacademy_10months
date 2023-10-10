package com.nhnacademy.aiot;

public class SimpleHandler {
    public void run() {
        String requestLine = reader.readLine();
        if(requestLine == null) {
            break;
        }

        String[] field = requestLine.split("\\s", 3);
        Request request = new Request(field[0], field[1], field[2]);

        String fieldLine;
        while ((fieldLine = reader.readLine())!= null) {
            log.trace("Field : {}", fieldLine);
            if(fieldLine.length()== 0) {
                break;
            }
            request.addField(fieldLine);
        }

        if(request.hasContentLength()) {
            
        }
        log.trace("[ REQUEST ]");
        log.trace(fieldLine.toString());
    }
    public static void main(String[] args) {
        
    }
}
