package com.nhnacademy.mart.employee;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

public class EmployeeGenerator implements Iterator<Employee>{
    private static final AtomicLong ID_GENERATOOR = new AtomicLong();

    private static final EmployeeGenerator INSTANCE = new EmployeeGenerator();

    public EmployeeGenerator getEmployeeGenerator(){
        return INSTANCE;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Employee next() {
        if(!hasNext()) {
            throw new NoSuchElementException();
        }

        return new Employee(ID_GENERATOOR.incrementAndGet(), "직원" + ID_GENERATOOR.get());
    }
}
