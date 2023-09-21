package com.nhnacademy;

import org.json.JSONObject;

public class Quiz04 {
    public static void main(String[] args) {
        Person_3 person = new Person_3("nhn", new Address_3(12345, "경남 김해"));
        JSONObject object = new JSONObject(person);

        object.put("name", "academy");
        
        System.out.println(object);
        
        
        Object addressObject = object.get("address");
        // put을 쓰고 싶다면 JSONObject로 형변한해준다.
        ((JSONObject)addressObject).put("city", "성남");

        System.out.println(object);
        

    }
}
