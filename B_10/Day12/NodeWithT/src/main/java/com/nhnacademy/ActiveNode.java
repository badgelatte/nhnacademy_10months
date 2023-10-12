package com.nhnacademy;

// thread를 이용해서 만듦
public abstract class ActiveNode extends Node implements Runnable{
    Thread thread;

    boolean stopped = true;
    int interval = 1;

    String status = "stopped";
    
    protected ActiveNode() {
        super();
    }

    protected ActiveNode(String id) {
        super(id);
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getInterval() {
        return interval;
    }

    public String getStatus() {
        return status;
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        thread.interrupt();
        stopped = true;
    }

    protected void preprocess() {
        status = "preprocess";
        stopped = false;
    }

    public void main() {
        status = "main";
    }

    protected void postprocess() {
        status = "postprocess";
    }

    public boolean isAlive() {
        // return !thread.isInterrupted();
        return !stopped;
    }

    @Override
    public void run() {
        preprocess();

        // 외부에서 접근할 수 있다
        long startTime = System.currentTimeMillis();
        long previousTime = startTime;

        while (isAlive()) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - previousTime;

            if (elapsedTime < interval) {
                try {
                    main();
                    Thread.sleep(interval - elapsedTime);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            previousTime = startTime + (System.currentTimeMillis() - startTime) / interval * interval;
        }

        postprocess();
    }
}
