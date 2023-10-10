package nhn;

public class ActiveNode extends Node implements Runnable{
    Thread thread;
    long startTime;
    long interval;

    public ActiveNode() {
        
    }
}
