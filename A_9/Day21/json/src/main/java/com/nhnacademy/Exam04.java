package com.nhnacademy;

import org.json.JSONObject;

public class Exam04 {
    public static void main(String[] args) {
        Person_3 person = new Person_3("nhn", new Address_3(12345, "경남 김해"));
        JSONObject object = new JSONObject(person);

        object.put("age", 10);
        System.out.println(object);
        object.put("age", 15);
        System.out.println(object);
    }
}
