package com.nhnacademy;

import java.net.Socket;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class Scurl 
{

    public static void main( String[] args ){
        int port = 3000;
        String host = "http://httpbin.org/get";


        Options options = new Options();

        options.addOption("v",  false, "verbose, 요청, 응답 헤더를 출력한다.");
        options.addOption(Option.builder("H").hasArgs().argName("line").desc("임의의 헤더를 서버로 전송한다.").build());
        options.addOption(Option.builder("d").hasArgs().argName("data").desc("POST, PUT 등에 데이터를 전송한다.").build());
        options.addOption(Option.builder("X").hasArgs().argName("commmand").desc("사용할 method를 지정한다. 지정되지 않은 경우, 기본값은 GET").build());
        options.addOption("L", false, "서버 응답이 30X 계열이면 다음 응답을 따라간다.");
        options.addOption(Option.builder("F").hasArgs().argName("name=conntet").desc("multipart/form-data를 구성하여 전송한다. content 부분에 @filename을 사용할 수 있다.").build());

        CommandLineParser parser = new DefaultParser();
        CommandLine commandLine;

        try{
            Socket socket = new Socket(host, port);
            commandLine = parser.parse(options, args);

            if(commandLine.hasOption("v")) {
                commandLine.getOptionValue("-verbose");
                System.out.println();
            }
            else if(commandLine.hasOption("H")) {
                commandLine.getOptionValue("H");
                System.out.println("Version: " );
            }
            else if(commandLine.hasOption("d")) {
                System.out.println("Version: " + commandLine.getOptionValue("d"));
            }
            else if(commandLine.hasOption("X")) {
                System.out.println("Version: " + commandLine.getOptionValue("X"));
            }
            else if(commandLine.hasOption("L")) {
                System.out.println("Version: " + commandLine.getOptionValue("L"));
            }
            else if(commandLine.hasOption("F")) {
                System.out.println("Version: " + commandLine.getOptionValue("F"));
            }
            // System.out.println();
        }catch(Exception e) {
            System.out.println(e);
            HelpFormatter formatter = new HelpFormatter();
            formatter.setOptionComparator(null);
            formatter.printHelp("scurl [option] url", options);
        }
    }
}
