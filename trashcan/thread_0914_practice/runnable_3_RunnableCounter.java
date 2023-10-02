// Quiz-01. Runnable interface를 이용해 RunnableCounter class를 구현해 보자.
// Thread를 이용하는 class는 앞서 소개한 Thread class 확장 외에 Runnable[1] interface[2]를 통해서도 정의할 수 있다.

public class runnable_3_RunnableCounter implements Runnable{
    String name;
    int maxCount;
    int count;

    public runnable_3_RunnableCounter(String name, int maxCount) {
        this.name = name;
        this.maxCount = maxCount;
        count = 0;
    }

    public void run() {
        try {
            while(count < maxCount) {
                count++;
                System.out.println(name +" : " + count);
                Thread.sleep(1000);
            }
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
    }

    public static void main(String[] args) {
        runnable_3_RunnableCounter counter = new runnable_3_RunnableCounter("runnableCounter", 5);
        Thread thread = new Thread(counter);
        thread.start();
    }
}