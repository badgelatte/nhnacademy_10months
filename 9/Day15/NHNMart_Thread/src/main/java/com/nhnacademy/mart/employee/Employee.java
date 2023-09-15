package com.nhnacademy.mart.employee;

import com.nhnacademy.mart.coupon.Coupon;

public class Employee {
    private final long id;

    private final String name;

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
