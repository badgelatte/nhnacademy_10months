package com.nhnacademy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main2 {
    public static void main(String[] args) {
        System.out.println(runTest("[fp]oo", "fooxxxxxxxpoo"));
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