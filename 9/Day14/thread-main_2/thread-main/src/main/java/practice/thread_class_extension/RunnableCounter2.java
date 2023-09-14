package practice.thread_class_extension;

public class RunnableCounter2 implements Runnable{  // Runnable로 만드는데 Thread와 일체화 시키기
    String name;
    int maxCount;
    int count = 0;
    static int totalCount = 0;

    public RunnableCounter2(String name, int maxCount) {
        this.name = name;
        this.maxCount = maxCount;
        count++;
        totalCount = 0;
    }

    public String getName() {
        return name + count;
    }

    public Thread getThread() {
        // if ()
        return Thread.currentThread();
    }

    @Override
    public void run() {
        Thread.currentThread().setName(name);
        while(count < maxCount) {
            try{
                totalCount++;
                System.out.println(getName() + " : " + totalCount);
                Thread.sleep(1000);
            } catch(InterruptedException ignore) {
                Thread.currentThread().interrupt();
            }
        }
        
    }
    public static void main(String[] args) {
        // Thread thread = new Thread(new RunnableCounter("runnableCounter", 10));
        // main에서 돌아간다고 한다
        RunnableCounter2 runnable = new RunnableCounter2("counter", 50);
        Thread thread_main = new Thread(runnable);
        Thread thread2 = new Thread(new RunnableCounter2("counter", 10));

        System.out.println(runnable.getThread().getName());
        thread_main.start();
        thread2.start();
        System.out.println(runnable.getThread().getName());


        // thread.start();
    }
}

