package practice.thread_class_extension;

public class ThreadCounter extends Thread{  // Thread class 이용하여 만들기
    int maxCount;
    int count;

    public ThreadCounter(String name, int maxCount) {
        setName(name);
        this.maxCount = maxCount;
        count = 0;
    }

    @Override
    public void run() {
        //while(count < maxCount) {
        for(int i = 0; i< maxCount; i++) {
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
        ThreadCounter threadCounter = new ThreadCounter("threadCounter", 50);

        threadCounter.start();
    }
}

