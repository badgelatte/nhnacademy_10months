package com.nhnacademy;

// 기본적으로 protected나 default로 써야한다
public abstract class Node {
    static int count;   // 모든 노드가 count를 공유하도록 static 사용
    String id;
    String name = "";

    protected Node() {
        //앞이 같아도 뒤에 count땜에 달라져서 ㄱㅊ
        this(String.valueOf(System.currentTimeMillis()) + (++count));    // Node(String id)를 쓰는 것이다
        // this.id = String.valueOf(System.currentTimeMillis());
    }

    protected Node(String id) {
        this.id = id;
    }
    
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // name은 세팅할 수 있다
    public void setName(String name) {
        this.name = name;
    }
    
    // 생성된 갯수만 확인 가능
    public static int getTotalCount() {
        return count;
    }
}
