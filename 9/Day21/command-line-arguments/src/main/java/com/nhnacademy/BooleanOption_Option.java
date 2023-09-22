package com.nhnacademy;

import java.text.ParseException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

public class BooleanOption_Option{
    public static void main(String[] args) {
        boolean booleanFlag = false;
        String version = "1.0.0";
        
        Options options = new Options();

        // opt: 짧은 버전의 명령어, longOpt : 긴 버전의 명령어, hasArg: 뒤에 추가적으로 인자가 필요한가, description: 해당 명령어에 대한 설명
        options.addOption("f", "flag", false, "상태를 출력한다");
        options.addOption("v", "version", false, "버전 출력");
        options.addOption("i", "id ", true, "식별자");
        
        CommandLineParser parser = new DefaultParser();
        CommandLine commandLine;
        // 
        // hasOption - 내가 말한 명령을 가지고 있니
        try {
            commandLine = parser.parse(options, args);
            if(commandLine.hasOption("v")) {
                System.out.println("Version: " + version);
                // System.exit(0);
                // exit에서 0 = 정상 종료, 0이 아닌 것은 비정상 종료
            } 
            
            if(commandLine.hasOption("f")) {
                booleanFlag = true;
                System.out.println("flag :" + booleanFlag);
       
            } 
            
            if(commandLine.hasOption("i")){
                System.out.println("ID: " + commandLine.getOptionValue("i"));
            }
            
        } catch (Exception e) {
            // System.out.println("옵션이 잘못 입력되었습니다.");
            System.out.println(e.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("test", options);
        }

    }
}