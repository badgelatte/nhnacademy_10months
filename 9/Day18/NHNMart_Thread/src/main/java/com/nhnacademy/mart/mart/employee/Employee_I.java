package com.nhnacademy.mart.mart.employee;

import com.nhnacademy.mart.mart.coupon.Coupon;

public class Employee_I implements Runnable{
    private final long id;

    private final String name;

    Thread thread;

    public Employee_I(long id, String name) {
        this.id = id;
        this.name = name;
        thread = new Thread(this);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void give(Coupon coupon) {
        while (!thread.isInterrupted()){
            // 쿠폰을 발급한다.
            // coupon.give();
        }
    }

    @Override
    public void run() {
        try{
            while(!thread.interrupted()) {
                // give(coupon);
            }
        } catch (Exception e) {
            System.out.println(" a ");
        }
    }
}
