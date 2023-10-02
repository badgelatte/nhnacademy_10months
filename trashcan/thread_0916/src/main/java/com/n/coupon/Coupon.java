package com.n.coupon;

public class Coupon {
    // 쿠폰 id, 쿠폰의 수량, 쿠폰의 종류 (10000원 쿠폰인지, % 할인인지 등.)
    private final long id;
    private final int amount;
    private final CouponType couponType;

    private Coupon(long id, int amount, CouponType couponType) {
        this.id = id;
        this.amount = amount;
        this.couponType = couponType;
    }

    public static Coupon of(long id, int amount) {
        return new Coupon(id, amount, CouponType.DISCOUNT);
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

    enum CouponType {
        DISCOUNT, PERCENT;
    }
}
