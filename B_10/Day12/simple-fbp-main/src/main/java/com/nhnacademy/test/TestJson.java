package com.nhnacademy.test;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.nhnacademy.node.TimerNode;

public class TestJson {
    public static void main(String[] args) {
        TimerNode timer = new TimerNode("Timer");

        System.out.println(timer.getJson().toJSONString());
        String jsonString = "{\"name\":\"Timer\",\"id\":\"fd8904cb-689b-11ee-9bb4-bd48e5f1aad8\"}";
        JSONObject newObject = (JSONObject) JSONValue.parse(jsonString);

        System.out.println(newObject.toJSONString());

    }
}
