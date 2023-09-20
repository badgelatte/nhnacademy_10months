package com.nhnacademy.dkjf;

import java.util.Arrays;

public class ChatServer {
    public static void main(String[] args) {
        // :를 중심으로 자른다.
        // split -> 공백이 있으면 에러가 뜬다 => 이를 방지하기 위해 trim을 쓴다
        String[] tokens = "ID:A".split(":");
        String[] tokenss = "#A:@B:Hello!".trim().split(":");
        // id @컴퓨터 ip주소 - github ssh방식
        System.out.println(Arrays.toString(tokens));
        System.out.println(Arrays.toString(tokenss));
    }
}
