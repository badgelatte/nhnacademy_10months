package com.nhnacademy;

import org.json.JSONObject;

public class Quiz03_person {
    public static void main(String[] args) {
        Person_3 person = new Person_3("nhn", new Address_3(12345, "경남 김해"));
        JSONObject personObject = new JSONObject(person);

        System.out.println(personObject);
    }
}
