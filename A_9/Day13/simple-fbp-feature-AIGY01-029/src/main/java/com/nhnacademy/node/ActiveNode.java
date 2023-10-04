package com.nhnacademy.node;

public abstract class ActiveNode extends Node implements Runnable{
    Thread thread;  // ActiveNode를 구동 시켜줄 Thread
    // long startTime; // 동작 시작 시각(동작 관리용)
    long interval = 1000;  // 내부적인 동작 주기
    boolean running;
    

    // 기본 생성자
    protected ActiveNode() {
        super();
        thread = new Thread(this, getId());
        running = false;
    } 

    // 이름 지정 생성자
    protected ActiveNode(String name) {
        this();
        setName(name);
    } 

    // getter & setter
    @Override
    public String getName() {
        return thread.getName();
    }

    @Override
    public void setName(String name) {
        thread.setName(name);
    }

    public void start() {
        thread.start();
    }
    
    public void stop() {
        running = false;
        thread.interrupt();
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    // 준비 과정
    void preprocess() { // -> override해서 쓰라고 놔둠
    }

    void process() {    // 지금은 active라 이게 필요가 없음

    }
    // 정리 과정
    void postprocess() {
    }

    @Override
    public void run() {
        preprocess();

        running = true;

        long startTime = System.currentTimeMillis();
        long previousTime = startTime;

        while(running) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - previousTime;  // 현재 시간 경과 시간 체크

            if(elapsedTime < interval) {
                try {
                    process();
                    Thread.sleep(interval - elapsedTime);

                } catch(InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            previousTime = startTime + (System.currentTimeMillis() - startTime) / interval * interval;
        }

        postprocess();
    }
    
}
