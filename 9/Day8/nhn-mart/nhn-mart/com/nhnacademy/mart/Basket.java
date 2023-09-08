package com.nhnacademy.mart;

import java.util.ArrayList;

public class Basket {
    private final ArrayList<Food> foods = new ArrayList<>();

    // TODO 05 장바구니에 물건을 담는 메서드를 작성하세요.
    public void addFoodBasket(Food food) {  // 만일 물건이 있다면 장바구니에 담기
        foods.add(food);

    }

    // TODO 06 장바구니에 있는 물건들을 반환하는 메서드를 작성하세요. // -> 물건들 반환이면 ArrayList인가? 아님 Lsit?
    public ArrayList getBasket() {
        String backetlist = "";
        /* for(int i= 0; foods == null; i++) {
            //foods.get();
            System.out.println(foods);
        } */
        return foods;
    }
}
