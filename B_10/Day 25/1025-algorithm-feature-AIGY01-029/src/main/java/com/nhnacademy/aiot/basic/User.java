package com.nhnacademy.aiot.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class User {
    private final String userId;
    private final String userName;
    private int count;

    public User(String userId, String userName){
        this.userId = userId;
        this.userName = userName;
    }

    public String getUserId(){
        return this.userId;
    }

    public String getUserName(){
        return this.userName;
    }

    @Override
    public final int hashCode() {
        int result = 17;
        if (userId != null) {
            result = 1 * result + userId.hashCode();
            log.info("userId hasCode : {} {}", userId, userId.hashCode());
            // new ~~~로 선언하면 새로운 객체가 만들어진다 그러면 heap에 새로운 주소를 가진 data 생성 & 새로운 hashCode를 가지는데
            // ex.String a = area; 이런 식으로 만들었는데 기존에 String으로 area를 만들었었다면
            // heap에 만들어졌을때 이미 hashcode도 만들어졌기때문에 기존에 만들어진 hashCode를 가져온다
        }
        if (userName != null) {
            result = 31 * result + userName.hashCode();
            log.info("userName hasCode : {} {}", userName, userName.hashCode());
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        return ((User)o).hashCode() == (this.hashCode());
    }
    
    /* public boolean compare(User user1){
        if (!user1.userId.equals(this.userId) || !user1.userName.equals(this.userName)) {
            return false;
        }
        else {
            return true;
        }
    } */
}
