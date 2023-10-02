package com.n.Employee;

import java.util.ArrayList;
import java.util.List;

import com.n.coupon.Coupon;

public class Employee {
    // id, 이름, 쿠폰의 수량
    private final long id;
    private final String name;
    static List<Coupon> couponList = new ArrayList<>();

    Employee(long id, String name) {
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
