package com.nhnacademy;

import org.json.JSONArray;
import org.json.JSONObject;

public class Quiz06 {
    public static void main(String[] args) {
        // JSONArray jsonArray = new JSONArray(new int[align=center,width=600]{1,2,3});
        String [] birds = new String[] {"갈매기", "참새", "펭귄"};
        String [] mammals = new String[] {"사자", "호랑이", "말"};
        String [] animals = {};

        JSONArray birdsArray = new JSONArray(birds);
        JSONArray mammalsArray = new JSONArray(mammals);
        JSONArray animalsArray = new JSONArray(animals);

        for (String bird : birds) {
            animalsArray.put(bird);
        }
        for(String mammal: mammals) {
            animalsArray .put(mammal);
        }



        // JSONObject object = new JSONObject(birdsArray);
        System.out.println(animalsArray);
        
        
    }
}