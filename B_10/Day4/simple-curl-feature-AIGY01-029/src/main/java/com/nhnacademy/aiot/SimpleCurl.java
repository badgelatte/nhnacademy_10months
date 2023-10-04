package com.nhnacademy.aiot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class SimpleCurl {
    StringBuilder builder = new StringBuilder();
    Options options;
    String url = "";
    String host = "localhost";
    int port = 80;
    String version = "HTTP/1.1";
    String path ="";
    String query = "";

    String html;
    String username = "";
    String password = "";

    String header;
    String data;

    boolean start = false;
    boolean mpost = false;

    // String message = ""; // 이거 맞냐?

    BufferedWriter writer;
    String line;

    String method = "GET";  // 아무것도 안하면 GET 출력

    List<String> dataList = new ArrayList<>();

    public SimpleCurl() {
        options = new Options();        
        
        options.addOption("v",  false, "verbose, 요청, 응답 헤더를 출력한다.");
        options.addOption(Option.builder("H").hasArg().argName("line").desc("임의의 헤더를 서버로 전송한다.").build());
        options.addOption(Option.builder("d").hasArg().argName("data").desc("POST, PUT 등에 데이터를 전송한다.").build());
        options.addOption(Option.builder("X").hasArg().argName("commmand").desc("사용할 method를 지정한다. 지정되지 않은 경우, 기본값은 GET").build());
        options.addOption("L", false, "서버 응답이 30X 계열이면 다음 응답을 따라간다.");
        options.addOption(Option.builder("F").hasArg().argName("name=conntet").desc("multipart/form-data를 구성하여 전송한다. content 부분에 @filename을 사용할 수 있다.").build());
        options.addOption("?", false, "사용법을 출력한다");

    }
    
    public void send(String message) throws IOException {
        writer.write(message + "\n");
        // writer.newLine();
        writer.flush();
    }
    
    //만약 서버만 적어서 보내면
    public void run() {
        // stringbuilder - 문자열을 다 합쳐줌
        // StringBuilder builder = new StringBuilder();
        // format - 문자열 형식을 설정하는 메서드 -> %s %s %s 문자열 그대로 출력
        builder.append(String.format("%s /%s %s \n", method, path, version));
        if(header != null) {
            builder.append(String.format("%s \n", header));
            if(data!= null) {
                builder.append(String.format("Content-Length: %s\n", data.length()));
            }
        }
        builder.append(String.format("Host: %s:%s\n", host, port));
        builder.append("\r\n");
        
        if(data != null) {
            builder.append(String.format("%s \n", data));
            builder.append("\r\n");
        }

        // nc test-vm.com 3000
        // GET /welcome.html HTTP/1.1
        // Host: test-vm.com:3000
        
        try (
            Socket socket= new Socket(host, port);
            BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); 
        ) {

            // 전송한 builder 보여주기
            writer.write(builder.toString());
            writer.flush();

            // 요청한 답변 출력하기
            if(start) {
                System.out.println("> " + builder.toString());
            }
            while ((line = reader.readLine())!= null) {
                if(line.length() == 0) {
                    start = true;
                }
                if(start) {
                    System.out.println("< " + line);
                }
                
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }


    public void showHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.setOptionComparator(null);
        formatter.printHelp("scurl", options);
    }

    public void setOption(CommandLine commandLine) throws ParseException{

            // option에 ?를 가지고 있다면
            if(commandLine.hasOption("?")){
                    showHelp();
                    System.exit(0);
            }
            // option에 X를 가지고 있다면 
            if(commandLine.hasOption("X")) {
                method = commandLine.getOptionValue("X");
    
                if(method.equals("POST")) {
                    mpost = true;
                }
                if(!((method.equalsIgnoreCase("GET")) || (method.equalsIgnoreCase("POST")) || (method.equalsIgnoreCase("PUT")))){
                    // throw new InvalidMethodException("Method 지정 잘못되었습니다.[GET, PUT, POST] :" + method);
                }
                String[] args = commandLine.getArgs();
                url = args[0];
            }
            
            if(commandLine.hasOption("d")) {
                if(mpost) {
                    data = commandLine.getOptionValue("d");
                }
            }
            
            // verbose, 요청, 응답 헤더 출력
            if(commandLine.hasOption("v")) {
                start = true;

            }

            if(commandLine.hasOption("H")) {
                header = commandLine.getOptionValue("H");

            }

            // 입력한 것이 아무것도 없다면
            if(commandLine.getArgs().length == 0) {
                throw new InvalidURLException();
            }

            String[] args = commandLine.getArgs();
            url = args[0];

    }

    public void argsOrganize(String url) {
        // <scheme>://<username>:<password>@<host>:<port>/<path>?<query>#<fragment>
        // https://nhnent.dooray.com/project/to?userWorkflowClass=registered,working
        // //로 자르고 그다음은 /로 자르고 그다음 :로 자른다
        // url.split("://")[1].split("/");을 아래처럼 나눈다
        String [] splitURL = url.split("://");
        // scheme = splitURL[0];

        // ://이 들어가지 않은 문자열이 입력되었을 시
        if(splitURL.length != 2) {
            System.out.println("잘못된 입력을 하였습니다.");
            System.exit(0);
        }
    
        // spiltURL[1] = httpbin.org/get
        // 0 - httpbin.org / 1 - get
        String [] field = splitURL[1].split("/");
        
        if(field[0].contains("@")) {
            // 0 - <username>:<password> / 1 - <host>:<port>
            String [] login = field[0].split("@");
            username = login[0].split(":")[0];
            password = login[0].split(":")[1];
            host = login[1].split(":")[0];
            port = Integer.parseInt(login[1].split(":")[1]);

        }
        else if(field[0].contains(":")) {
            //있으면 나누고 
            host = field[0].split(":")[0];
            port = Integer.parseInt(field[0].split(":")[1]);
        } else {
            host = field[0];
            path = field[1];
        }
        if(field[1].contains("?")) {
            String[] splitField = field[1].split("?");
            path = splitField[0];
            query = splitField[1];
        }
    }


    public static void main(String[] args){
        SimpleCurl scurl = new SimpleCurl();
        // 공용 클래스 DefaultParser는 Object를 확장하여 CommandLineParser를 구현합니다
        CommandLineParser parser = new DefaultParser();
        try {
            // 저장된 options에 따라 인수를 구문 분석한다
            CommandLine commandLine = parser.parse(scurl.options, args);
            scurl.setOption(commandLine);
            scurl.argsOrganize(scurl.url);
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
