package com.nhnacademy.mart.mart.thread;

import javax.management.RuntimeErrorException;

import com.nhnacademy.mart.thread.Request;

// shoppingchannel에서 받은 request 처리하는 worker (= 직원) 만들기
public class ShoppingWorker implements Runnable{

    private final ShoppingChannel shoppingChannel;

    public ShoppingWorker (ShoppingChannel shoppingChannel) {
        this.shoppingChannel = shoppingChannel;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            i++;
            // 여기 올 일 없어서 무한 루프 걸림
            if(i == Integer.MIN_VALUE) {
                break;
            }

            Request request = null;
            try{
                request = shoppingChannel.getRequest();
                request.execute();
            } catch(InterruptedException e) {
                // 이거 써서 위로 던져버리겠다
                throw new RuntimeException(e);
            }
        }
    }
    
}
