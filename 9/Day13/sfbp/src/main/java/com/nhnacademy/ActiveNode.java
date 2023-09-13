package com.nhnacademy;

public class ActiveNode extends Node implements Runnable{
    Thread thread;  // ActiveNode를 구동 시켜줄 Thread
    long startTime; // 동작 시작 시각(동작 관리용)
    long interval;  // 내부적인 동작 주기
    boolean running;

    // 기본 생성자
    public ActiveNode() {
        super();
        thread = new Thread(this, getId());
        running = false;
    }

    // 이름 지정 생성자
    public ActiveNode(String name) {
        this();
        setName(name);
    } 

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
        thread.interrupt();
    }

    void preprocess() {
    }

    void process() {

    }

    void main() {

    }

    void idle() {

    }

    void postprocess() {

    }

    @Override
    public void run() {
        preprocess();

        running = true;

        while(Thread.currentThread().isInterrupted()){
            main();
            idle();
            try {
                process();
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        postprocess();
    }
}
