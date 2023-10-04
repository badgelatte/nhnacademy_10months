package com.nhnacademy.mart.mart.thread;

import java.util.concurrent.ThreadLocalRandom;

import com.nhnacademy.mart.customer.Customer;
import com.nhnacademy.mart.mart.Basket;
import com.nhnacademy.mart.mart.food.Food;
import com.nhnacademy.mart.thread.Request;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShoppingRequest extends Request{

    private final Customer customer;

    public ShoppingRequest(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public void execute() {
        // 가격을 100에서 900 사이로 랜덤하게 줬다
        Basket.addFood(new Food("오이", ThreadLocalRandom.current().nextInt(100, 900)));
        Basket.addFood(new Food("당근", ThreadLocalRandom.current().nextInt(100, 900)));
        Basket.addFood(new Food("사과", ThreadLocalRandom.current().nextInt(100, 900)));
        
        // 고객이 누구인지, 장바구니에 뭐가 있는지 보여줌
        log.info("customer:{}, 장바구니:{}", customer.getId(), Basket.info());

        Basket.reset();
    }
    
}
