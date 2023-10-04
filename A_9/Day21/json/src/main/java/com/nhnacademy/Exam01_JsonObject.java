package com.nhnacademy;

import org.json.JSONObject;

public class Exam01_JsonObject {
    public static void main(String[] args) {
        JSONObject object = new JSONObject();
        object.put("name", "nhn");
        System.out.println(object);
        
    }
}