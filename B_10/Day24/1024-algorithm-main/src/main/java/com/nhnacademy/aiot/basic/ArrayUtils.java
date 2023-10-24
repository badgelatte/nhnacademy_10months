package com.nhnacademy.aiot.basic;

public final class ArrayUtils {

    public ArrayUtils(){
        throw new IllegalStateException();
    }

  public static boolean contains(Object[] objects, Object find){
    //todo objects array에서 find가 존재하면 true 존재하지 않다면 false를 반환합니다.
    
    for(Object o : objects){
        if(o.equals(find)){
            return true;
        }
    }
    return false;
  }

}