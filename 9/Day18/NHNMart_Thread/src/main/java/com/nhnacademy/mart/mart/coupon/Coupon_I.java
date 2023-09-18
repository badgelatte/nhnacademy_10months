package com.nhnacademy.mart.mart.coupon;

public class Coupon_I {
    private final long id;

    private final int amount;

    private final CouponType couponType;

    // 쿠폰 총 갯수
    private final int maxCoupon = 50;

    public Coupon_I(long id, int amount, CouponType couponType) {
        this.id =id;
        this.amount = amount;
        this.couponType = couponType;
    }

    public static Coupon_I ofDiscount(long id, int amount) {
        return new Coupon_I(id, amount, CouponType.DISCOUNT);
    }

    public long getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public CouponType getCouponType() {
        return couponType;
    }


    static int count = 0;
    // 쿠폰 주기 -> 공유된 자원
    public void give() {
        synchronized(this) {
            while (count < maxCoupon) {
                count++;
            }
        }
    }

    enum CouponType {
        DISCOUNT,
        PERCENT;
    }

}
