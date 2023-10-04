package com.nhnacademy.mart.thread;

import com.nhnacademy.mart.employee.Employee;
import com.nhnacademy.mart.employee.EmployeeGenerator;


public class WorkerPool {
    private final Worker[] workers;

    // Pool 사이즈 = poolSize
    public WorkerPool(int poolSize, Channel channel) {
        // 공간 확보
        workers = new Worker[poolSize];

        // 실제 channel 만들기
        for(int i = 0; i < poolSize; i++) {
            // 일하는 사람 ex. 직원수 3명을 만듦
            Employee employee = EmployeeGenerator.getEmployeeGenerator().next();
            workers[i] = new Worker(channel, employee);
        }
    }

    // 실행시키기까지 만듦
    public void start() {
        for(Worker worker : workers) {
            new Thread(worker).start();
        }
    }
}
