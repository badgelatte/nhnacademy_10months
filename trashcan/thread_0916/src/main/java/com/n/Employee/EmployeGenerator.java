package com.n.Employee;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

public class EmployeGenerator implements Iterator<Employee>{
    private static final EmployeGenerator INSTANCE = new EmployeGenerator();
    private static final AtomicLong IDGENERATOR = new AtomicLong();

    public static EmployeGenerator getEmployeGenerator() {
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
        return new Employee(IDGENERATOR.incrementAndGet(), "직원" + IDGENERATOR.get());
    }

}
