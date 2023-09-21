package com.nhnacademy;

import org.json.JSONObject;

public class Quiz01_print {
    public static void main(String[] args) {
        JSONObject object = new JSONObject();
        // object.put("address", object.put("code", 13487), object.put("city", "Seongnam"),"name", "nhn");
        JSONObject address = new JSONObject();

        /* address.put("address", 12345);
        address.put("name", "nhn");
        object.put("code", address);
        object.put("city", "Seongnam"); */

        address.put("code", 13487);
        address.put("city", "Seongnam");
        object.put("name", "nhn");
        object.put("address", address);

        System.out.println(object);
    }
}
