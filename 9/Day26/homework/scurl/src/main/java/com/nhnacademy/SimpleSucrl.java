package com.nhnacademy;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class SimpleSucrl {
Options options;
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
    public void showHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.setOptionComparator(null);
        formatter.printHelp("scurl", options);
    }

    public CommandLine setOption(String[] args) throws ParseException{
            CommandLineParser parser = new DefaultParser();
        return parser.parse(options, args);
    }

    public static void main( String[] args ){
        SimpleSucrl scurl = new SimpleSucrl();
        
        try {
            CommandLine commandLine = scurl.setOption(args);
            if(commandLine.hasOption("?")){
                scurl.showHelp();
            }
        } catch (ParseException e) {
            scurl.showHelp();
        }
    }
}
