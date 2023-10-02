package com.n.coupon;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

public class CouponGenerator implements Iterator<Coupon>{
    // 최대 수량, 쿠폰 ID(Atomic) , CouponGenerator.
    private static final int COUPON_MAX = 50;
    private static final AtomicLong IDGENERATOR = new AtomicLong();
    private static final CouponGenerator INSTANCE = new CouponGenerator();

    public static final  CouponGenerator getCouponGenerator() {
        return INSTANCE;
    }

    public synchronized boolean hasNext() {
        return IDGENERATOR.get() < COUPON_MAX;
    }

    @Override
    public Coupon next() {
        if(!hasNext()) {
            throw new NoSuchElementException();
        }
        return Coupon.of(IDGENERATOR.incrementAndGet(), 10000);
    }
    
}
