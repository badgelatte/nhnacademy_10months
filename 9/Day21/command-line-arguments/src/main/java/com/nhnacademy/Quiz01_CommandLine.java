package com.nhnacademy;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

// -ac --path -t bash -- path
public class Quiz01_CommandLine {
    public static void main(String[] args) {
        Options options = new Options();

        options.addOption("ac", "ac", false, "ddd");
        options.addOption("path", "path", false, "path");
        options.addOption("t", "t", false, "t");

        CommandLineParser parser = new DefaultParser();
        CommandLine commandLine;

        try {
            commandLine = parser.parse(options, args);
            if(commandLine.hasOption("ac")) {
                System.out.println("-ac!");
            } 
            if(commandLine.hasOption("path")) {
                System.out.println("path!");
            }
            /* if(commandLine.hasOption("-t")) {

            } */
        } catch (Exception e) {
            System.out.println("-t 옵션이 누락되었습니다.");
        }
    }
}
