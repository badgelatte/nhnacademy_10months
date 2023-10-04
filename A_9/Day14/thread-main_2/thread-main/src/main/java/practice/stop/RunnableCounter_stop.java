package practice.stop;

public class RunnableCounter_stop implements Runnable{  // Runnable로 만드는데 Thread와 일체화 시키기
    Thread thread;
    String name;
    int maxCount;
    int count;
    boolean runningFlag;

    public RunnableCounter_stop(int maxCount) {
    }

    public RunnableCounter_stop(String name, int maxCount) {
        this.name = name;
        this.maxCount = maxCount;
        count = 0;
        runningFlag = false;
    }

    public String getName() {
        return name;
    }

    public Thread getThread() {
        // if ()
        return Thread.currentThread();
    }

    public void start() {
        thread.start();
    }

    public void stop() {
        runningFlag = false;
        // thread.interrupted();
    }

    @Override
    public void run() {
        Thread.currentThread().setName(name);
        runningFlag = true;

        // thread.isInterrupted() // -> 일반 인스턴스 
        while(runningFlag && (count < maxCount)) {
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
        RunnableCounter_stop ruunableCounter0 = new RunnableCounter_stop(10);
        RunnableCounter_stop ruunableCounter1 = new RunnableCounter_stop(10);

        System.out.println(ruunableCounter0.getName());

    }
}

