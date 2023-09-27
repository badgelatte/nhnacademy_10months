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
    String version = "HTTP/1.1";
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
             
            

            // option에 ?를 가지고 있다면
            if(commandLine.hasOption("?")){
                    showHelp();
                    System.exit(0);
            }
            // option에 X를 가지고 있다면 
            if(commandLine.hasOption("X")) {
                method = commandLine.getOptionValue("X");
    
                if(!(method.equalsIgnoreCase("GET")) || !(method.equalsIgnoreCase("POST")) || !(method.equalsIgnoreCase(""))){
                    throw new InvalidMethodException("Method 지정 잘못되었습니다.[GET, PUT, POST] :" + method);
                }
            }
            
            if(commandLine.hasOption()) {

            }

            // 입력한 것이 아무것도 없다면
            if(commandLine.getArgs().length == 0) {
                throw new InvalidURLException();
            }


            //--------------------------------------------------------------------------------------------
            // 입력한 것 중 공백 기준으로 첫번째 문자열 = url로 넣는다.
            url = commandLine.getArgs()[0];
        
            // //로 자르고 그다음은 /로 자르고 그다음 :로 자른다
            url.split("://")[1].split("/");
            String [] splitURL = url.split("://");
            if(splitURL.length != 2) {
                
            }
        
            // ://로 나눈 후 뒷부분을 /로 나눠준다.
            String [] field = splitURL[1].split("/");
            if(field[0].contains(":")) {
                //있으면 나눠서 앞부분은 host에 넣고 뒷부분은 port에 넣는다
                host = field[0].split(":")[0];
                port = Integer.parseInt(field[0].split(":")[1]);
            }
            field[0].split(":");    //...?

        return parser.parse(options, args);
    }

    public void setOption() {
        
    }


    public static void main( String[] args ){
        SimpleSucrl scurl = new SimpleSucrl();
        
        try {
            
            scurl.setOption();
            scurl.run();

        } catch (InvalidMethodException e) {
            scurl.showHelp();
        } catch (InvalidURLException e) {
            scurl.showHelp();
            System.err.println("<scheme>://<host>:<port>[<path>][?<query>]");
        }
    }
}
