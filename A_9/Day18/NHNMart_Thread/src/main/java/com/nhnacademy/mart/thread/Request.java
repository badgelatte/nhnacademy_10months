package com.nhnacademy.mart.thread;

import java.util.concurrent.atomic.AtomicLong;

public abstract class Request {
    // request도 중복되면 안되니까 Atomic 사용
    private static final AtomicLong ID_GENERATOR = new AtomicLong();

    private final long requesetId;

    // 내가 쿠폰을 받기위한 요청일 수도 잇고 결제하려는 요청일 수도 있으니까 
    // 확장성을 고려해서 설계 해보려고 이렇게 함
    public Request() {
        this.requesetId = ID_GENERATOR.incrementAndGet();
    }

    // abstract로 확장한 메소드는 이렇게 적어야함 -> ...?
    public abstract void execute();

    public String toString() {
        return "requestId= " + requesetId;
    }
}
