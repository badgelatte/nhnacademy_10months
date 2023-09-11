package prctice;

public class SharedCounter implements Runnable{
    Thread thread;
    int count = 0;
    int maxCount;
    SharedCount sharedCount;    // 공유 카운터

    public SharedCounter(String name, int maxCount, SharedCount sharedCount){
        thread = new Thread(this, name);
        this.maxCount = maxCount;
        this.sharedCount = sharedCount;
    }

    public int getCount() {
        return count;
    }

    public void start() {
        //내가 가진 스레드를 실행
        thread.start();
    }

    public void stop() {
        thread.interrupt();
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted() && (count < maxCount)) {
            // !Thread.currentThread().isInterrupted() -> 
            count++;
            sharedCount.increment();    // 여기를 마킹만 잘하면 안 막힌다. -> 여기만 잘 막으면 괜찮다
        }
    }

    public boolean isAlive() {
        return thread.isAlive();
    }

    public static void main(String[] args) {
        SharedCount sharedCount = new SharedCount();
        SharedCounter counter1 = new SharedCounter("counter1", 10000, sharedCount);
        SharedCounter counter2 = new SharedCounter("counter2", 10000, sharedCount);

        counter1.start();
        counter2.start();

        while(counter1.isAlive() || counter2.isAlive());

        System.out.println("counter1: " + counter1.getCount());
        System.out.println("counter2: " + counter2.getCount());
        System.out.println("ShareCount: " +sharedCount.getCount());
    }

}
