package com.n.customer;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

public class CustomerGenerator implements Iterator<Customer>{
    private static final CustomerGenerator INSTANCE = new CustomerGenerator();
    private static final AtomicLong IDGENERATOR = new AtomicLong();

    public static CustomerGenerator getCustomerGenerator() {
        return INSTANCE;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Customer next() {
        if(!hasNext()) {
            throw new NoSuchElementException();
        }
        // 왜 Customer 클래스의 of 메소드 static 써야하는 거지?
        return Customer.of(IDGENERATOR.incrementAndGet(), "고객" + IDGENERATOR.get());
    }
    
}
