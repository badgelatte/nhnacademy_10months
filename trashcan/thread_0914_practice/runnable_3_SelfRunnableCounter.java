// Exam-02. RunnableCounter에 Thread field를 추가하고, 이를 이용해 실행해 보자

// 구현되는 class내에서 Thread를 field로 포함

public class runnable_3_SelfRunnableCounter implements Runnable{
    String name;
    int maxCount;
    int count;
    Thread thread; 

    public runnable_3_SelfRunnableCounter(String name, int maxCount) {
        this.name = name;
        this.maxCount = maxCount;
        count = 0;
        thread = new Thread(this);
    }

    public void start(){
        thread.start();
    }

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
        runnable_3_SelfRunnableCounter counter = new runnable_3_SelfRunnableCounter("runnableCounter", 5);
        counter.start();
    }
}