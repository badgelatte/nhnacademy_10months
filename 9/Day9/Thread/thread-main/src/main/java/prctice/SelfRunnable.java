package prctice;

public class SelfRunnable implements Runnable{ // made by teacher
    int count = 0;  // 모든게 0으로 초기화 된다
    String name;
    int maxCount;

    Thread thread;
    boolean running = false;    // 상태를 알리고 제어하는데 사용 -> 동작 가능의 여부를 표시

    public SelfRunnable(String name, int maxCount) {
        this.name = name;
        this.maxCount = maxCount;
        this.thread = new Thread(this);
    }

    public void start() {
        thread.start();
    }

    public void stop() {
        running = false;
    }

    public boolean isRunning() {    // 상태 알려주기
        return running;
    }

    @Override
    public void run() {
        running = true;     // 상태가 시작으로 바뀜
        thread = Thread.currentThread();

        try {
            while(running && (count < maxCount)) {  // running 하면서 조건문일 동안 돌아라
                count++;
                System.out.println(name + " : " + count);
                // Thread.sleep(1000);     // 1초동안 쉰다
                Thread.sleep(10000);    // 10초 기다리면 아직 한참 있다가 끝난다.
                // 자고 있는 중의 시간이 기니까 이렇게 차이가 난다
            }

        } catch (InterruptedException e) {  
            Thread.currentThread().interrupt(); // -> currentThread -> t1
        }
        System.out.println(name + " finished");
    }

    public Thread getThread() {
        return thread;
    }

    public static void main(String[] args) throws InterruptedException{
        SelfRunnable runnableCounter1 = new SelfRunnable("runnableCounter1", 5);
        SelfRunnable runnableCounter2 = new SelfRunnable("runnableCounter2", 5);

        runnableCounter1.start();
        runnableCounter2.start();

        Thread.sleep(3000);
        runnableCounter1.stop();
    }
}
