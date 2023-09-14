package practice.Store;

import java.util.concurrent.ThreadLocalRandom;




public class Producer implements Runnable {
    Thread thread;
    Store store;

    public Producer(Store store) {
        this.store = store;
        thread = new Thread(this);
    }

    public void start(){
        thread.start();   
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            try {
    // 생산자는 매장에 물건이 부족하지 않도록 채워둔다.
                store.sell();
   
    
    // 물건은 1~10초 간격으로 채운다. 
    // Thread내에서 난수 생성을 위해서는 ThreadLocalRandom.current().nextInt()를 사용하면 된다.
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        }

    }
}