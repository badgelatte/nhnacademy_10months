package com.n.customer;

import java.util.ArrayList;
import java.util.List;

import com.n.coupon.Coupon;

public class Customer {
    // id, 이름, 수중의 돈, 쿠폰 리스트
    // getCouponList, 생성자 of -> 1000000만원을 가지고 있다고 설정

    
    private final long id;
    private final String name;
    private final int mount;
    private final List<Coupon> couponList = new ArrayList<>();

    private Customer(long id, String name, int mount) {
        this.id = id;
        this.name = name;
        this.mount = mount;
    }

    public static Customer of(long id, String name) {
        return new Customer(id, name, 1000000);
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
    
    public int getMount() {
        return mount;
    }
    
    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", mount=" + mount + ", couponList=" + couponList + "]";
    }
    
}
