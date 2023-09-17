package com.nhnacademy.mart.coupon;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

public class CouponGenerator implements Iterator<Coupon>{
    private static final int COUPON_MAX_SIZE = 50;
    private static final AtomicLong ID_GENERATOR = new AtomicLong();

    // 싱글톤 사용을 위해 
    private static final CouponGenerator INSTANCE = new CouponGenerator();

    // 가져다 써야하니까
    public static CouponGenerator getCouponGenerator() {
        return INSTANCE;
    }

    // 쿠폰 최대 수보다 작으면 ID_GENERATOR를 준다.
    @Override
    // 순서대로 호출할 수 있도록 synchronized 걸어준다.
    public synchronized boolean hasNext() {
        return ID_GENERATOR.get() < COUPON_MAX_SIZE;
    }


    @Override
    public Coupon next() {
        // 쿠폰                            
        if(!hasNext()) {
            throw new NoSuchElementException();
        }
        return Coupon.ofDiscount(ID_GENERATOR.incrementAndGet(), 10_000);
    }
}
