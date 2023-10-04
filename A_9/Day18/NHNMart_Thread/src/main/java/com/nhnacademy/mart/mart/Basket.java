package com.nhnacademy.mart.mart;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Style;

import com.nhnacademy.mart.mart.food.Food;

// 바구니
public class Basket {
    //  1번
    // private final List<Food> foodList = new ArrayList<>();

    // 2번
    // 멤버 쪽에 장바구니 추가해서 사용해야한다
    // 장바구니 관리하는 거 만들어서 사용
    // 가장 쉽게 하려면 customer에다가 추가 -> 하지만 사용 안함
    
    // private final ThreadLocal<List<Food>> foodLocl = new ThreadLocal<>();
    // 이렇게 쓰면 nullpoint에러뜬다 -> 3번 초기화
    
    // Thread 내에서만 접근이 가능하지, 초기화되는게 아니다. 내부에서만 접근하고 외부에서 접근하는게 아니다
    // 
    private static final ThreadLocal<List<Food>> foodLocal = ThreadLocal.withInitial((ArrayList::new));
    // 아래 묶음을 위의 한 문장으로 만듦
    /*  private final ThreadLocal<List<Food>> foodLocl = new ThreadLocal<>();
    public Basket() {
        foodLocl.set(new ArrayList<>());
    } */
    
    public static void addFood(Food food){
        // foodList.add(food);
        foodLocal.get().add(food);
    }

    public static List<Food> getFoodList() {
        return foodLocal.get();
    }

    // 지워짐
    public static void reset() {
        foodLocal.remove();
    }

    public static String info() {
        List<Food> foods = foodLocal.get();
        StringBuilder stringBuilder = new StringBuilder();
        // STringBuilder - 긴 문자열을 
        // 메모리 공간에 할당되면 특정 문자열을 할당하면, 다른이름의 문자열에 할당하면 할당된 녀석을 가르킴 -> 메모리 공간 절약
        // 매번 더하면 새로운 얘가 되서 새로운 공간을 계속 할당함

        // 문자열을 관리하는 게 있는데 거기에 넣고 동일한 문자는 한 곳을 바라보게함
        // 같은 문자열을 합쳐도 새로운 문자열로 나옴 -> 새로운 공간 만듦
        // -> 공간차지 많아진다
        // StringBuilder -> 공간을 확보해서 거기에 붙인다


        for (Food food : foods) {
            stringBuilder.append(food);
            stringBuilder.append(System.lineSeparator());
            // lineSeparator - 플랫폼마다 처리하는게 다르기때문에 이거 씀
            
        }
        // stringBuilder -> 내부적으로 byte형태의 공간 확보해서 다 합쳐서 꺼냄
        return stringBuilder.toString();
    }


}
