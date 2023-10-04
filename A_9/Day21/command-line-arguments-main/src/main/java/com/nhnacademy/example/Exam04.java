package com.nhnacademy.example;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Exam04 {
    public static void main(String[] args) {
        Options options = new Options();

        options.addOption("a", null, false, "Option a");
        options.addOption("b", null, false, "Option b");
        options.addOption("v", "version", false, "Version");

        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("a")) {
                System.out.println("-a : a 옵션이 추가되어 있습니다.");
            }

            if (cmd.hasOption("b")) {
                System.out.println("-b : b 옵션이 추가되어 있습니다.");
            }

            if (cmd.hasOption("v")) {
                System.out.println("-v : print the verison");
            }

        } catch (ParseException ignore) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp(Exam04.class.getSimpleName(), options);
        }
    }
}
