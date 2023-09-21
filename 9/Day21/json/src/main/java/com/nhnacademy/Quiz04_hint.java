package com.nhnacademy;

import org.json.JSONObject;

public class Quiz04_hint {
    public static void main(String[] args) {
        Person_3 person = new Person_3("nhn", new Address_3(12345, "경남 김해"));
        JSONObject object = new JSONObject(person);

        object.put("age", 10);
        System.out.println(object);
        // object.put("age", 15);
        // System.out.println(object);

        // 찾았을 때 jsonobject는 2rkwlfktj 2개의 공통적인 object를 받는다.?

        // JSONObject addressObject = (JSONObject)object.get("address");
        Object addressObject = object.get("address");
        // if(addressObject instanceof JSONObject) {
            System.out.println((JSONObject)addressObject);
        // }


    }
}
