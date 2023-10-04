package practice.state;

public class ThreadInstance implements Runnable{
    Thread thread;

    String name;
    int maxCount;
    int count;

    public ThreadInstance(String name, int maxCount) {
        thread = new Thread(this);
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

    public void start(){
        thread.start();
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
        Thread thread = new Thread();
        System.out.println(thread.getState());

        ThreadInstance threadInstance = new ThreadInstance("ThreadInstance", 5);
        threadInstance.start();
        System.out.println(threadInstance.getThread().getState());
    }
}

