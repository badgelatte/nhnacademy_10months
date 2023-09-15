package com.nhnacademy.mart.customer;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

// Iterator -> 계속 입력이 들어오는데 arrayList에 다 넣을 수 없어서,
public class CustomerGenerator implements Iterator<Customer>{
    
    // static -> heap에 올라감 => 공유해서 쓸라고 올림
    private static final CustomerGenerator INSTANCE = new CustomerGenerator();
    
    // Atomic이 들어가면 synchronized를 안하면 더하기가 엉망이 되는데 이를 방지하기 위해 하나씩 증가시키는 것도 메소드로 제공해준다
    private static final AtomicLong ID_GENERATOR = new AtomicLong();
    
    // 외부에서 접근 못하는 INSTANCE를 외부에서 가져다 써야할 때 static으로 하나 만들어서 
    // 내부의 INSTANCE 값을 공유한다 -> 싱글톤
    public static CustomerGenerator getCustomerGenerator() {
        // 한번 만들고 부를때마다 쓰겠다
        return INSTANCE;
    }
    
    /* public static Customer getCustomer() {
        // incrementAndGet() -> 하나 증가시키고 가져온다.   getAndIncrement() -> 가져오고 증가시킨다.
        // ID_GENERATOR.get() -> 앞에서 증가하고 가져왔으니까 이거는 그냥 가져오면 된다.
        return Customer.of(ID_GENERATOR.incrementAndGet(), "고객" + ID_GENERATOR.get());
    } */

    // 다음 자료 있는가 물어보는거
    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Customer next(){
        // 다음이 없다면
        if(!hasNext()){
            throw new NoSuchElementException();
        }
        return Customer.of(ID_GENERATOR.incrementAndGet(), "고객" + ID_GENERATOR.get());
    }
}
