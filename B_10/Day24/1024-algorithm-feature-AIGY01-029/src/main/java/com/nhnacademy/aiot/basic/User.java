package com.nhnacademy.aiot.basic;

import java.util.Objects;

public class User {

  private final String userId;
  private final String userName;

  public User(String userId, String userName){
    this.userId = userId;
    this.userName = userName;
  }

  public String getUserId() {
    return userId;
  }

  public String getUserName() {
    return userName;
  }

  //todo user 객체의 비교가 될 수 있도록 구현합니다.
  public static boolean isSame(User user1, User user2) {
    //equals -> data 비교 / == -> 주소값 비교
    if(user1.userId.equals(user2.userId) && user1.userName.equals(user2.userName)){
        return true;
    }
    return false;
    // return (userId.equals(userName));
  }
}

