package com.nhnacademy.node;

// doc 설명과 동일하게 만든 코드
public abstract class ActiveNode_doc extends Node implements Runnable{
    Thread thread;  // ActiveNode를 구동 시켜줄 Thread
    // long startTime; // 동작 시작 시각(동작 관리용)
    long interval;  // 내부적인 동작 주기
    boolean running;
    

    // 기본 생성자
    protected ActiveNode_doc() {
        super();
        thread = new Thread(this, getId());
        running = false;
    } 

    // 이름 지정 생성자
    protected ActiveNode_doc(String name) {
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

    // 준비 과정
    void preprocess() { // -> override해서 쓰라고 놔둠
    }

    void process() {    // 지금은 active라 이게 필요가 없음
    }

    void main() {
    }

    void idle() {
    }

    // 정리 과정
    void postprocess() {
    }

    @Override
    public void run() {
        preprocess();

        running = true;

        while(Thread.currentThread().isInterrupted()) {
            main();
            idle();
            try {
                process();
                Thread.sleep(interval);

            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        postprocess();
    }
    
}
