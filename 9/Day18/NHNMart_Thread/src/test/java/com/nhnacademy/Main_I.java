package com.nhnacademy;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.nhnacademy.mart.coupon.Coupon;
import com.nhnacademy.mart.customer.Customer;
import com.nhnacademy.mart.employee.Employee;

/**
 * Hello world!
 *
 */
public class Main_I 
{
    public static void main( String[] args ) {
        // Customer customer = Customer.of(1, "marco");

        // 이렇게 매번 종류 적다 실수 할 수 있으니까 종류를 통일하기 위해 ofDiscount 제작
        // Coupon coupon = new Coupon(1, 10_000, CouponType.DISCOUNT);

        // Coupon coupon = Coupon.ofDiscount(1, 10_000);

        // 고객을 무한대로 만들어서 줄을 세운다
        while(true) {
            int i = 1;
            Customer customer = Customer.of(i++, "customer");
            // customer.start();
        }
    }
}
