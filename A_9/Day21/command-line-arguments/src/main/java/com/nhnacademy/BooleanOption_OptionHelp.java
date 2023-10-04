package com.nhnacademy;

import java.text.ParseException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

public class BooleanOption_OptionHelp{
    public static void main(String[] args) {
        boolean booleanFlag = false;
        String version = "1.0.0";
        
        Options options = new Options();

        options.addOption("f", "flag", false, "상태를 출력한다");
        // 해당 기능을 선택할 수 있는 boolean 옵션
        // b,c 는 언제한거야??? ㅁㅊ
        options.addOption("v", "version", false, "버전 출력");
        options.addOption("i", "id ", true, "식별자");
        options.addOption("?", "help", true, "식별자");
        
        CommandLineParser parser = new DefaultParser();
        CommandLine commandLine;
        // 
        // hasOption - 내가 말한 명령을 가지고 있니
        try {
            commandLine = parser.parse(options, args);
            if(commandLine.hasOption("?")){
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("test", options);
            }
            else if(commandLine.hasOption("v")) {
                System.out.println("Version: " + version);
                System.exit(0);
            } else if(commandLine.hasOption("f")) {
                booleanFlag = true;
            } else if(commandLine.hasOption("i")){
                System.out.println("ID: " + commandLine.getOptionValue("i"));
            }

            // man, xtra는 argumentextra로 구분된다.
            System.out.println(commandLine.getArgList().toString());
        } catch (Exception e) {
            // System.out.println("옵션이 잘못 입력되었습니다.");
            System.out.println(e.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("test", options);
        }

    }
}