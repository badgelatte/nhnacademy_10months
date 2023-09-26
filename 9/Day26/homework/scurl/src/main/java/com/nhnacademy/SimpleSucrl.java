package com.nhnacademy;

import java.net.Socket;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class SimpleSucrl {
    Options options;
    String url = "";
    String host = "localhost";
    int port = 80;
    String version = ";"
    static String method = "GET";  // 아무것도 안하면 GET 출력
    public SimpleSucrl() {
        options = new Options();        
        
        options.addOption("v",  false, "verbose, 요청, 응답 헤더를 출력한다.");
        options.addOption(Option.builder("H").hasArgs().argName("line").desc("임의의 헤더를 서버로 전송한다.").build());
        options.addOption(Option.builder("d").hasArgs().argName("data").desc("POST, PUT 등에 데이터를 전송한다.").build());
        options.addOption(Option.builder("X").hasArgs().argName("commmand").desc("사용할 method를 지정한다. 지정되지 않은 경우, 기본값은 GET").build());
        options.addOption("L", false, "서버 응답이 30X 계열이면 다음 응답을 따라간다.");
        options.addOption(Option.builder("F").hasArgs().argName("name=conntet").desc("multipart/form-data를 구성하여 전송한다. content 부분에 @filename을 사용할 수 있다.").build());
        options.addOption("?", false, "사용법을 출력한다");

    }
    
    
    public void run() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%s %s %s", method, url, version));

        Socket socket= new Socket(host, port);
        socket.getOutputStream().write(...);
    }


    public void showHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.setOptionComparator(null);
        formatter.printHelp("scurl", options);
    }

    public CommandLine setOption(String[] args) throws ParseException{
            CommandLineParser parser = new DefaultParser();
            CommandLine commandLine = parser.parse(options, args);

            if(commandLine.hasOption("?")){
                    showHelp();
                    System.exit(0);
                }
            if(commandLine.hasOption("X")) {
                    method = commandLine.getOptionValue("X");
        
                    if(!(method.equalsIgnoreCase("GET")) || !(method.equalsIgnoreCase("POST")) || !(method.equalsIgnoreCase(""))){
                        throw new InvalidMethodException("Method 지정 잘못되었습니다.[GET, PUT, POST] :" + method);
                    }
        
                }
            if(commandLine.getArgs().length == 0) {
                throw new InvalidURLException();
            }
            url = commandLine.getArgs()[0];
        
            // //로 자르고 그다음은 /로 자르고 그다음 :로 자른다
            url.split("://")[1].split("/");
            String [] splitURL = url.split("://");
            if(splitURL.length != 2) {
        
            }
        
            //
            String [] field = splitURL.split("/");
            if(field[0].contains(":")) {
                //있으면 나누고 
                host = field[0].split(":")[0];
                port = Integer.parseInt(field[0].split(":")[1]);
            }
            field[0].split(":");

        return parser.parse(options, args);
    }

    public void setOption() {
        
    }


    public static void main( String[] args ){
        SimpleSucrl scurl = new SimpleSucrl();
        
        try {
            // 
            
            scurl.setOption();
            scurl.run();


        } catch (ParseException e) {
            scurl.showHelp();
        } catch (InvalidMethodException e) {
            scurl.showHelp();
        } catch (InvalidURLException e) {
            scurl.showHelp();
        }
    }
}
