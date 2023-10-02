// Exam-02. RunnableCounter에 Thread field를 추가하고, 이를 이용해 실행해 보자

// 구현되는 class내에서 Thread를 field로 포함

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class runnable_3_ThreadPool implements Runnable{
    String name;
    int maxCount;
    int count;
    Thread thread; 

    public runnable_3_ThreadPool(String name, int maxCount) {
        this.name = name;
        this.maxCount = maxCount;
        count = 0;
        thread = new Thread(this);
    }

    public void start(){
        thread.start();
    }

    /* public void stop() {
        
    } */

    public void run() {
        try {
            while(count < maxCount) {
                count++;
                System.out.println(name + " : " + count);
                Thread.sleep(1000);
            }
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
    }

    public static void main(String[] args) {
        // runnable_3_ThreadPool counter = new runnable_3_ThreadPool("runnableCounter", 5);
        // counter.start();
        ExecutorService pool = Executors.newFixedThreadPool(1);

        pool.execute(new runnable_3_ThreadPool("counter1", 5));
        pool.execute(new runnable_3_ThreadPool("counter2", 5));
        
        try {
            pool.shutdown();

            System.out.println("Shutdown called");
            
            // https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html
            // awaitTermination
            //종료 요청 후 모든 작업의 ​​실행이 완료되거나, 시간 초과가 발생하거나, 현재 스레드가 중단될 때까지 차단합니다.
            while (!pool.awaitTermination(2, TimeUnit.SECONDS)) {
                // 특정 시간 후 
                // SECONDS : 1초를 나타내는 시간 단위
                System.out.println("Not yet finished");
            }

            System.out.println("All service finished");
        } catch (Exception e) {
        }
            
    }
}