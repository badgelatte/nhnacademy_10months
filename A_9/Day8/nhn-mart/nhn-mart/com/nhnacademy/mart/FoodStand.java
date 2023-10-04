package com.nhnacademy.mart;

import java.util.ArrayList;

public class FoodStand {

    private final ArrayList<Food> foods = new ArrayList<>();

    // TODO 03 매대에 물건을 추가하는 메서드를 구현하세요
    public void addFoodMart(Food food) {
        foods.add(food);
    }
    
    // TODO 04 매대에 있는 특정 물건을 골라서 반환하는 메서드 구현하세요
    public void setFood(Food food) {
        if(foods.contains(food)) {
            foods.remove(food);  // 해당 음식 빼내기
        }
        else {
            System.out.println("there is no " + food);
        }
    }
}
