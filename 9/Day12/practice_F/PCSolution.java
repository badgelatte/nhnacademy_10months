import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class PCSolution {
    public static void main(String[] args) {
        Store store = new Store(5);
        // Consumer consumer = new Consumer("1", store);
        Producer producer = new Producer(store);

        producer.start();
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {
            executor.submit(new Consumer("고객" + (i + 1), store));
        }
        executor.shutdown();
        while (!executor.isTerminated()) {

        }

    }

}
