package com.nhnacademy.mart.mart.employee;

import java.util.concurrent.LinkedBlockingQueue;

import com.nhnacademy.mart.mart.coupon.Coupon;

public class Employee {
    private final long id;

    private final String name;

    static LinkedBlockingQueue<Coupon> couponAccount = new LinkedBlockingQueue<Coupon>(50);

    public Employee(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
