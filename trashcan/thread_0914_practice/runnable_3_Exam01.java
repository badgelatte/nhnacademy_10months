// Exam-01. Thread object 생성 후 종료될때 자동 삭제하도록 만들어 보자
// 생성 후 종료될때 자동 삭제

public class runnable_3_Exam01 implements Runnable{
    String name;
    int maxCount;
    int count;

    public runnable_3_Exam01(String name, int maxCount) {
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

// main()이 먼저 종료하는 것은 thread.join()을 통해 대기 가능하다.
    public static void main(String[] args) throws InterruptedException {
        runnable_3_Exam01 counter1 = new runnable_3_Exam01("runnableCounter", 5);
        runnable_3_Exam01 counter2 = new runnable_3_Exam01("runnableCounter", 5);
        Thread thread1 = new Thread(counter1);
        Thread thread2 = new Thread(counter2);

        thread1.start();
        thread2.start();

        thread2.join();
        System.out.println("finished");

    }
}