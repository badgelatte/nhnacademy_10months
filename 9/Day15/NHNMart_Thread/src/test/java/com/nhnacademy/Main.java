package com.nhnacademy;

import com.nhnacademy.mart.coupon.Coupon;
import com.nhnacademy.mart.customer.Customer;
import com.nhnacademy.mart.customer.CustomerGenerator;
/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args ) {
        Customer customer = Customer.of(1, "marco");

        // 이렇게 매번 종류 적다 실수 할 수 있으니까 종류를 통일하기 위해 ofDiscount 제작
        // Coupon coupon = new Coupon(1, 10_000, CouponType.DISCOUNT);

        Coupon coupon = Coupon.ofDiscount(1, 10_000);

        // 호출만 하면 무한대로 생성됨
        // CustomerGenerator.getCustomer();

        // 안됨 -> singleton땜에
        // 쓸려고 하는 곳에 
        CustomerGenerator customerGenerator = new CustomerGenerator();

        // tq 왜 안됌?
        for(int i = 0; i < 100; i++) {
            Customer customer1 = CustomerGenerator.getCustomerGenerator().next();
        }

        // log.info("customer:{}", customer1);

        
    }
}
