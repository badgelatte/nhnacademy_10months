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
        if (getUserId() != null) {
            result = 1 * result + getUserId().hashCode();
            log.info("userId hasCode : {} {}", getUserId(), getUserId().hashCode());
            // new ~~~로 선언하면 새로운 객체가 만들어진다 그러면 heap에 새로운 주소를 가진 data 생성 & 새로운 hashCode를 가지는데
            // ex.String a = area; 이런 식으로 만들었는데 기존에 String으로 area를 만들었었다면
            // heap에 만들어졌을때 이미 hashcode도 만들어졌기때문에 기존에 만들어진 hashCode를 가져온다
        }
        if (getUserName() != null) {
            result = 31 * result + getUserName().hashCode();
            log.info("userName hasCode : {} {}", getUserName(), getUserName().hashCode());
        }
        return result;
    }

    // 같은 지를 찾을 때 hashcode로 찾고 그 다음에 주소값을 찾아 비교한다
    // hashcode는 극악의 확률로 다른 객체가 같은 수를 가질 수도 있다
    // equals했을때 모든 주소를 일일히 비교하기엔 힘드니까 비교하려는 것의 hashcode를 찾아가서 주소값을 가져와 비교한다
    @Override
    public boolean equals(Object o) {
        if(o.getClass() != User.class){
            return false;
        } 
        return (((User)o).userId == this.userId && ((User)o).userName == this.userName);
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
