package com.nhnacademy.mart.thread;

import com.nhnacademy.mart.customer.Customer;
import com.nhnacademy.mart.customer.CustomerGenerator;
import com.nhnacademy.mart.mart.thread.ShoppingChannel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Client extends Thread {
    private final Channel channel;
    private final ShoppingChannel shoppingChannel;

    public Client(Channel channel, ShoppingChannel shoppingChannel) {
        this.channel = channel;
        this.shoppingChannel = shoppingChannel;
    }

    @Override
    public void run() {
        while(true) {   // 무한정으로 돌게 하는 거
            // 고객이 하는 요청을 만듦
            Customer customer = CustomerGenerator.getCustomerGenerator().next();
            Request requeset = new CouponRequest(customer, shoppingChannel);

            // 총체적으로 하는 거
            // 채널이 linkedList로 만듦 -> 그 링크드 리스트에 요청을 추가하고 그 요청을 빼내는 그런 역할을 하는 channel을 공유함
            // customer을 계속 생성해서 쿠폰 리퀘스트를 만들어서 고객의 요청을 채널안에 있는 buffer에다가 쌓겠다.
            channel.addRequest(requeset);

            try {
                // 무한정으로 도니가 sleep을 안주면 계속 무한정으로 다이렉트로 꽂아버림
                Thread.sleep(100);
                
            } catch (InterruptedException e) {
                log.error("", e);
            }

        }
    }
}
