package com.nhnacademy.mart.threadlocal;

import java.util.HashMap;
import java.util.Map;

public class UserStore {
    private final Map<Integer, String> userMap = new HashMap<>();
    

    public UserStore() {
        userMap.put(1, "marco");
        userMap.put(2, "jone");
    }

    // 유저 아이디를 얻는다
    public String getUserNameForUserId(Integer userID) {
        return userMap.get(userID).toString();
        
        // return userMap.get(userID);
    }
}
