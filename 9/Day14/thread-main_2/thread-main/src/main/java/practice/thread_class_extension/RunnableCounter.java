package practice.thread_class_extension;

public class RunnableCounter implements Runnable{  // Runnable로 만드는데 Thread와 일체화 시키기
    String name;
    int maxCount;
    int count;

    public RunnableCounter(String name, int maxCount) {
        this.name = name;
        this.maxCount = maxCount;
        count = 0;
    }

    public String getName() {
        return name;
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
                count++;
                System.out.println(getName() + " : " + count);
                Thread.sleep(1000);
            } catch(InterruptedException ignore) {
                Thread.currentThread().interrupt();
            }
        }
        
    }
    public static void main(String[] args) {
        // Thread thread = new Thread(new RunnableCounter("runnableCounter", 10));
        // main에서 돌아간다고 한다
        RunnableCounter runnable = new RunnableCounter("counter", 50);
        Thread thread_main = new Thread(runnable);

        System.out.println(runnable.getThread().getName());
        thread_main.start();
        System.out.println(runnable.getThread().getName());


        // thread.start();
    }
}

