package com.nhnacademy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Intersection {
    public static void main(String[] args) {
        System.out.println(runTest("[1-3&&[7-9]]", "4"));
    }

    public static int runTest(String regex, String text) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        
        int matches = 0;
        while (matcher.find()) {
            matches++;
        }
        return matches;
    }
}