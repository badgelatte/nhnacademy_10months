import java.util.concurrent.ThreadLocalRandom;

public class Producer implements Runnable {
    Thread thread;
    Store store;
    int minTime = 1000;
    int maxTime = 5000;

    public Producer(Store store) {
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
            store.sell();
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }
}