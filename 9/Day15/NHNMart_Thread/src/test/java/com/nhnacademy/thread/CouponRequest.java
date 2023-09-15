package com.nhnacademy.thread;

import com.nhnacademy.mart.coupon.CouponGenerator;
import com.nhnacademy.mart.customer.Customer;

import lombok.extern.slf4j.Slf4j;

// 요청 중에 coupon요청을 위한 class 
@Slf4j
public class CouponRequest extends Request {    // Request 클래스 확장해서 사용하는 거라 Request에 있는 거 무조건 구현해야함
    private final Customer customer;

    
    public CouponRequest(Customer customer) {
        this.customer = customer;
    }

    @Override
    protected void execute() {
        // 쿠폰 발급 로직

        if(CouponGenerator.getCouponGenerator().hasNext()) {
            customer.addCoupon(CouponGenerator.getCouponGenerator().next());
            log.info("there-id:{}, customer-id:{}, coupon-id:{}", 
            Thread.currentThread().getId(), customer.getId(), customer.getCouponList().get(0).getId());
        }
        else {

            log.info("there-id:{}, customer-id:{}, coupon-id:{}", 
            Thread.currentThread().getId(), customer.getId(), "Empty");
        }
        try{
            Thread.sleep(500);
        } catch (InterruptedException e) {
            log.error("sleep", e);
        }
        
            // 쿠폰을 발급받기 위해 손님들이 줄을 선다 (1번 요청)
            // 물건을 결제하려고 쿠폰을 줄라고 줄을 선다 (2번 요청)
            // ..등의 이러한 요청을 처리하기위해 여기에 관련 로직 작성
    } 
    



}
