package com.nhnacademy.example.exam05;

import com.beust.jcommander.JCommander;

public class Exam05 {
    public static void main(String[] args) {
        Options options = new Options();
        JCommander commander = JCommander.newBuilder()
                .programName(Exam05.class.getSimpleName())
                .addObject(options)
                .build();

        commander.parse(args);

        if (options.help) {
            commander.usage();
            return;
        }
        System.out.println("log : " + options.verbose.intValue());
        System.out.println("groups : " + options.groups);
        System.out.println("debug : " + options.debug);
    }
}
