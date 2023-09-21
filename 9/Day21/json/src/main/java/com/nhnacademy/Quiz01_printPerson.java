package com.nhnacademy;

import org.json.JSONObject;

public class Quiz01_printPerson {
    public static void main(String[] args) {
        JSONObject object = new JSONObject();
        JSONObject address = new JSONObject();


        address.put("code", 13487);
        address.put("city", "Seongnam");
        object.put("name", "nhn");
        object.put("address", address);

        // getName을 통해 name이 들어오는 거다
        // 그래서 getName2라고 적으면 출력도 name2라고 뜬다.
        // => get을 떼고 소문자로 바꿔서 get뒤를 모두 출력한다
        Person person = new Person("아카데미");
        JSONObject personObject = new JSONObject(person);

        object.put("별명", personObject);

        System.out.println(object);
    }
}
