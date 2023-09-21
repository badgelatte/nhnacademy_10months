package com.nhnacademy;

import org.json.JSONArray;
import org.json.JSONObject;

public class Quiz06_correct {
    public static void main(String[] args) {
        JSONArray array1 = new JSONArray(new String[] {"갈매기", "참새", "펭귄"});
        JSONArray array2 = new JSONArray(new String[] {"사자", "호랑이", "말"});
        JSONObject object1 = new JSONObject();
    
        JSONObject object2 = new JSONObject();
        object1.put("조류", array1);
        object2.put("포유류", array2);

        JSONArray array3 = new JSONArray(new JSONObject[] {object1, object2});
        JSONObject object3 = new JSONObject();
        object3.put("동물", array3);

        System.out.println(object3);
        System.out.println(object3.getJSONArray("동물").get(0));

        
    }
}
