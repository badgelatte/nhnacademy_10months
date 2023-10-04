package com.nhnacademy.mart.threadlocal;

import javax.management.RuntimeErrorException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SharedUserContext implements Runnable{

    // Threadlocal 사이의 session이라는 형태로 set할 수 있다
    // 객체의 범위가 Thread 내에서만 존재하여 공유해서 쓸 수 있다.
    private static final ThreadLocal<Session> userContext = new ThreadLocal<>();
    // private static final이니까 한번 만들어지고 유지가 되면 객체의 내용이 바뀌지 않는다
    //-> marco나 jone 둘 중 하나만 나온다
    // thread 내에서만 공유가 되기 때문에 내용물이 바뀌는 거다.

    // 원래 Heap에 내용물이 저장되는데 Thread가 실행되는 범위 내에서만 공유
    // 해당 스레드가 시작해서 죽을때까지 그 내에서만 공유한다.

    // channel은 다양하게 넓게 다 공유하기 위해 사용하는거지만 Session은 해당 Thread 내에서만 공유하는 것이다.

    private final Integer userId;

    private UserStore userStore = new UserStore();

    public SharedUserContext(Integer userId) {
        this.userId = userId;
    }

    @Override
    public void run() {
        String username = userStore.getUserNameForUserId(userId);
        userContext.set(new Session(username));
        try {
            Thread.sleep(100);
            
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
        log.info("session userId:{}, userName:{}",userId, userContext.get().getUserName());
    }
}
