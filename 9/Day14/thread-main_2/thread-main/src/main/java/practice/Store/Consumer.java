package practice.Store;

import java.util.concurrent.ThreadLocalRandom;


public class Consumer implements Runnable {
    Thread thread;  // Runnable을 implements하면 이렇게 thread를 사용하면 된다
    // 이거 외의 방법은 extends Thread를 쓰면 된다.
    Store store;
    String name;
    

    public Consumer(String name, Store store) {
        thread = new Thread(this);
        this.name = name;
        this.store = store;
    }

    public void start(){
        thread.start();   
    }

    public void stop(){
        thread.interrupt();
    }

    public String getName(){
        return name;
    }

    // 소비자는 매장에 입장 후 물건을 구매할 수 있다.
    // 매장에 입장하면 물건을 구매하고, 퇴장한다.
    @Override
    public void run() {
        if(!Thread.currentThread().isInterrupted()){ // thread가 interrupted(중단된 상태)가 아니라면 실행
            try {
                // 입장
                store.enter(thread.getName());
                
                // 물건 구매
                store.buy();
                // 1~10초 간격으로 구매한다.
                long waitingTime = ThreadLocalRandom.current().nextLong(1000, 5000);
                Thread.sleep(waitingTime);
        
                // 퇴장
                store.exit(thread.getName());

                // stop();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // 중단된 상태라면 
            }
        }
    }
}
