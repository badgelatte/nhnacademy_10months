package com.nhnacademy.aiot.basic;

public final class ArrayUtils {
    private static User user;
    // 유틸리티 클래스처럼 사용하는 것 => 생성자 만드는 것을 막는 것이다
    public ArrayUtils() {
        throw new IllegalStateException();
    }

  public static boolean contains(Object[] objects, Object find){
    //todo objects array에서 find가 존재하면 true 존재하지 않다면 false를 반환합니다.
    
    for (Object object : objects) {
        if (object == find) {
            return true;
        }
        if(object.getClass() == User.class && find.getClass() == User.class){
            User objectUser = (User)object;
            User findUser = (User)find;
            return User.isSame(objectUser, findUser);
        }
    }
    return false;
  }

}