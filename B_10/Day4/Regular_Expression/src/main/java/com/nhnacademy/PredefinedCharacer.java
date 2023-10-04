package com.nhnacademy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PredefinedCharacer {
    public static void main(String[] args) {
        System.out.println(runTest("\\d", "44"));
    }
    // d = 숫자 일치 [0-9]
    // 대문자 D = 숫자 아닌 것 일치
    // s = 공백일치
    // S = 공백 아닌 것 일치
    // w = [a-zA-Z_0-9](네임 만들때 쓰는것들의 범위랑 거진 동일) 내의 것들을 찾아라 
    // W = 단어문자 아닌 것
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