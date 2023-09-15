package com.nhnacademy.mart.customer;

import java.util.ArrayList;
import java.util.List;

import com.nhnacademy.mart.coupon.Coupon;

public class Customer_I {
    private final long id;

    private final String name;
    
    private int money;
    Thread thread;
    
    private final List<Coupon> couponList = new ArrayList<>();

    private Customer_I(long id, String name, int money) {
        this.id = id;
        this.name =name;
        this.money = money;
    }

    // of라는 메소드를 쓰면 가지고 있는 돈을 100만원으로 지정해서 생성해준다.
    public static Customer_I of(long id, String name) {
        return new Customer_I(id, name, 1000000);
    }

    public void addCoupon(Coupon coupon) {
        couponList.add(coupon);
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    // 고객은 쿠폰을 받는다.
    public void produce() {
        while (true) {
            
        }
    }

}
