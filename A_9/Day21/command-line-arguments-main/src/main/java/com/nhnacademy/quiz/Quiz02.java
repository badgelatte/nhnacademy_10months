package com.nhnacademy.quiz;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Quiz02 {
    public static void main(String[] args) {
        Options options = new Options();

        options.addOption("a", null, true, "Add file");
        options.addOption("r", null, true, "Remove file");
        options.addOption("c", null, false, "Remove all file");
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("a")) {
                System.out.println(cmd.getOptionValue("a") + "이 추가되었습니다.");
            }
            if (cmd.hasOption("r")) {
                System.out.println(cmd.getOptionValue("r") + "이 삭제되었습니다.");
            }
            if (cmd.hasOption("c")) {
                System.out.println("모든 파일이 삭제되었습니다.");
            }
        } catch (ParseException ignore) {
            System.err.println("인수가 잘못되었습니다.");
        }
    }
}
