import java.util.concurrent.ThreadLocalRandom;

public class Consumer implements Runnable {
    Store store;
    Thread thread;
    int minTime = 1000;
    int maxTime = 5000;

    public Consumer(String name, Store store) {
        thread = new Thread(this);
        this.store = store;
    }

    public void start(){
        thread.start();
    }

    public void stop(){
        thread.interrupt();
    }

    @Override
    public void run() {
        try {
            long waiting = minTime + ThreadLocalRandom.current().nextInt(maxTime - minTime);
            Thread.sleep(waiting);
            store.enter();
            store.buy();
            store.exit();
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }
}