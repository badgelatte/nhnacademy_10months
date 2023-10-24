package com.nhnacademy.aiot.general;

public interface IStack<E> {
  //Stack의 아래에서부터 데이터를 저장
  void push(E item);
  //Stack 맨 위의 값을 삭제
  E pop();
  //stack의 맨 위의 값을 가져옴
  E peek();
  //Stack에서 해당 객체가 몇 번째에 있는지 반환합니다.
  int search(E o);
  //Stack이 비어있는지 확인합니다. 비어있으면 true를 반환
  boolean empty();
}
