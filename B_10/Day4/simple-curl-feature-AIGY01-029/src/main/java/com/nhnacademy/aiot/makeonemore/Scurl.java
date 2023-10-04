package com.nhnacademy.aiot.makeonemore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class Scurl {
    Options options;
    String url;

    String host = "localhost";
    int port = 80;
    String version = "HTTP/1.1";
    String method = "GET";
    String path;   
    String query;

    String header;
    String data;

    String line;
    boolean start = false;

    public Scurl() {
        // scurl 객체를 만들면 options을 가지고 있음
        options = new Options();
        
        options.addOption("v", false, "verbose, 요청, 응답 헤더를 출력한다.");
        options.addOption(Option.builder("H").hasArg().argName("line").desc("임의의 헤더를 서버로 전송한다.").build());
        options.addOption(Option.builder("d").hasArg().argName("data").desc("POST, PUT 등에 데이터를 전송한다.").build());
        options.addOption(Option.builder("X").hasArg().argName("commmand").desc("사용할 method를 지정한다. 지정되지 않은 경우, 기본값은 GET").build());
        options.addOption("L", false, "서버의 응답이 30x 계열이면 다음 응답을 따라 간다.");
        options.addOption(Option.builder("F").hasArg().argName("name=conntet").desc("multipart/form-data를 구성하여 전송한다. content 부분에 @filename을 사용할 수 있다.").build());
        options.addOption("?", false, "사용법을 출력한다");

    }

    public void showHelp() {
        HelpFormatter formatter = new HelpFormatter();
            formatter.setOptionComparator(null);
            formatter.printHelp("scurl", options);
    }
    public void setOption(CommandLine commandLine) {
        if(commandLine.hasOption("?")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.setOptionComparator(null);
            formatter.printHelp("scurl", options);
        }
        if(commandLine.hasOption("v")) {
            start = true;
        }
        if(commandLine.hasOption("H")){

        }
        if(commandLine.hasOption("d")){
            
        }
        if(commandLine.hasOption("X")){
            
        }
        if(commandLine.hasOption("L")){
            
        }
        if(commandLine.hasOption("F")){
            
        }
        if(commandLine.getArgs().length == 0){
            System.err.println("아무것도 입력하지 않았습니다.");
        }

        // 위의 option 외의 args들
        String[] args = commandLine.getArgs();
        url = args[0];
        argsParser(url);
    }

    public void argsParser(String url) {
        // http://httpbin.org/get

        String[] splitUrl = url.split("://");   // 0 - http, 1 - httpbin.org/get
        if(splitUrl.length < 2){
            System.out.println("잘못된 주소 입력");
            System.exit(0);
        }
        else {
            // tokens 0 - httpbin.org, 1 - get
            String [] tokens = splitUrl[1].split("/");
            host = tokens[0];
            path = tokens[1];
            if(tokens[1].contains("?")) {
                query = tokens[1].split("?")[0];
            }
        }
    }

    public void run() {
        // GET /welcome.html HTTP/1.1       -- method / path version
        // Host: test-vm.com:3000           -- Host: host:port

        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%s /%s %s\n", method, path, version));
        // builder.append(method +" /"+ path + " "+ version +"\n");
        builder.append(String.format("Host: %s %s\n", host, port));
        builder.append("\n");

        try (
            Socket socket = new Socket(host, port);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        ) {
            // 요청 보내기
            writer.write(builder.toString());
            writer.flush();

            if(start) {
                String[] ot = builder.toString().split("\n");
                for (String string : ot) {
                    System.out.println("> " + string);
                }
                System.out.println();
            }
            // 요청 받은 거 출력하기
            while ((line = reader.readLine())!= null) {
                if(line.length() == 0) {
                    start = true;
                }
                if(start) {
                    System.out.println("< " + line);
                }
            }
        } catch (Exception e) {
            System.out.println("연결 실패 - " + e);
        }
    }

    public static void main(String[] args) {
        Scurl scurl = new Scurl();
        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine commandLine = parser.parse(scurl.options, args);

            scurl.setOption(commandLine);
            scurl.run();
        } catch (Exception e) {
            scurl.showHelp();
        }
    }
}
