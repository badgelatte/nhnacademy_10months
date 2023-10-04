package com.nhnacademy;

import java.text.ParseException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

public class BooleanOption2{
    public static void main(String[] args) {
        boolean booleanFlag = false;
        String version = "1.0.0";
        
        Options options = new Options();

        options.addOption("f", "flag", false, "상태를 출력한다");
        // 해당 기능을 선택할 수 있는 boolean 옵션
        options.addOption("v", "version", false, "버전 출력");
        // option, long option, version, 설명 -> 이제 쓸 수 있는 명령 등록함

        CommandLineParser parser = new DefaultParser();
        CommandLine commandLine;
        // 
        // hasOption - 내가 말한 명령을 가지고 있니
        try {
            commandLine = parser.parse(options, args);
            if(commandLine.hasOption("v")) {
                System.out.println("Version: " + version);
                System.exit(0);
            } else if(commandLine.hasOption("f")) {
                booleanFlag = true;
            }
        } catch (Exception e) {
            System.out.println("옵션이 잘못 입력되었습니다.");
        }

    }
}