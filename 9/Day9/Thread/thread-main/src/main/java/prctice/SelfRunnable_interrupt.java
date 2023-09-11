package prctice;

public class SelfRunnable_interrupt implements Runnable{ // made by teacher
    int count = 0;  // 모든게 0으로 초기화 된다
    String name;
    int maxCount;

    Thread thread;
    boolean running = false;    // 상태를 알리고 제어하는데 사용

    public SelfRunnable_interrupt(String name, int maxCount) {
        this.name = name;
        this.maxCount = maxCount;
        this.thread = new Thread(this);
    }

    public void start() {
        thread.start();
    }

    public void stop() {
        thread.interrupt();     // 스레드에다가 알려줄거ㅓ다 --> ????
    }

    public boolean isRunning() {    // 상태 알려주기
        return thread.isAlive();    // thread가 살아있는가?
    }

    @Override
    public void run() {
        running = true;     // 상태가 시작으로 바꿈
        thread = Thread.currentThread();

        // Thread.currentThread().isInterrupted() - interrupted가 떳나 안떳나만 확인하고 초기화는 하지 않는다
        while(!Thread.currentThread().isInterrupted() && (count < maxCount)) { 
            // Thread.interrupted() && 과 동일   // Thread.interrupted() - interrupted 초기화하지만 관계없다
            try {
                count++;
                System.out.println(name + " : " + count);
                Thread.sleep(10000);
            }

            catch (InterruptedException ignore) {  
                // InterruptedException ignore 이걸 빼먹으면 compile 오류 뜬다
                // Thread에 interrupt가 발생하였다고, 과정을 종료할 필요는 없다. Interrupt 발생 후 처리 과정은 다양하게 활용할 수 있다
                // -> flag를 넣을 수도 있고 다른 방법도 많다
                Thread.currentThread().interrupt();
                // interrupt에 setting을 해준다
            }
        } 
        System.out.println(name + " finished");
    }

    public Thread getThread() {
        return thread;
    }

    public static void main(String[] args) throws InterruptedException{
        SelfRunnable_interrupt counter1 = new SelfRunnable_interrupt("counter1", 5);
        SelfRunnable_interrupt counter2 = new SelfRunnable_interrupt("counter2", 5);

        counter1.start();
        counter2.start();

        Thread.sleep(3000);
        counter1.stop();
    }
}
