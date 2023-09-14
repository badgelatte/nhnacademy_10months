package practice.thread_class_extension;

public class RunnableCounter2 implements Runnable{  // Runnable로 만드는데 Thread와 일체화 시키기
    String name;
    int maxCount;
    int count = 0;
    static int totalCount = 0;

    public RunnableCounter2(int maxCount) {
        totalCount++;
        this.name = String.format("%s-%d",getClass().getSimpleName(),totalCount);
        this.maxCount = maxCount;
        count = 0;
    }

    
    public RunnableCounter2(String name, int maxCount) {
        totalCount++;
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
        Thread.currentThread().setName(getName());
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
        RunnableCounter2 runnable = new RunnableCounter2(50);
        Thread thread1 = new Thread(runnable, "Im Thread");
        Thread thread2 = new Thread(new RunnableCounter2(50));

        // 상태 바뀌는 걸 확인하기 위해 위아래 sysout한 거 아니었나??
        System.out.println(runnable.getThread().getName());
        thread1.start();
        thread2.start();
        System.out.println(runnable.getThread().getName());

        System.out.println(thread1.getName());
        System.out.println(thread2.getName());

        // thread.start();
    }
}

