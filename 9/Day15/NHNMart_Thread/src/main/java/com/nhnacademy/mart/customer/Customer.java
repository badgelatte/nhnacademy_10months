package com.nhnacademy.mart.customer;

import java.util.ArrayList;
import java.util.List;

import com.nhnacademy.mart.coupon.Coupon;

public class Customer {
    private final long id;

    private final String name;
    
    private int money;
    
    private final List<Coupon> couponList = new ArrayList<>();

    private Customer(long id, String name, int money) {
        this.id = id;
        this.name =name;
        this.money = money;
    }

    // of라는 메소드를 쓰면 가지고 있는 돈을 100만원으로 지정해서 생성해준다.
    public static Customer of(long id, String name) {
        return new Customer(id, name, 100_0000);
    }

    public void addCoupon(Coupon coupon) {
        couponList.add(coupon);
    }

    public List<Coupon> getCouponList() {
        return couponList;
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

    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", money=" + money + ", couponList=" + couponList + "]";
    }

    
}
