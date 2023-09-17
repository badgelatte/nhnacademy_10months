package com.nhnacademy.mart.thread;

import com.nhnacademy.mart.employee.Employee;
import com.nhnacademy.mart.employee.EmployeeGenerator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Worker implements Runnable{
    // 소비자 - queue를 소비하는 사람 = 직원

    // 물건을 꺼낼 channel 필요 
    private final Channel channel;

    // 직원 필요
    private final Employee employee;


    // 소비자가 파는데 
    public Worker(Channel channel, Employee employee) {
        this.channel = channel;
        this.employee = employee;
    }

    // 계속 소비시켜줘야 함
    @Override
    public void run() {
        while(true){
            // 하나씩 빼야함
            Request request = channel.takeRequeset();

            // 쿠폰 발급해야함
            if(request != null) {
                // request가 null이 뜨면  예외 처리됨
                request.execute();
                log.info("직원:{}이 쿠폰을 발급했습니다.", employee.getName());
            }


        }
    }
}
