public class ThreadCounter extends Thread {
    int count;  // 여기서 초기화 해도 된다
    String name;
    int maxCount;

    Thread thread;

    public ThreadCounter(String name, int maxCount) {
        this.name = name;
        this.maxCount = maxCount;
        count = 0;      // 모든게 0으로 초기화 된다
        thread = new Thread();
    }

    @Override   // super class(Thread)에서 상속받아서 덮어쓰기 때문
    public void run() {
        try {
            thread.start();
            while(count < maxCount) {
                count++;
                System.out.println(name + " : " + count);
                Thread.sleep(1000);     // 1초동안 쉰다
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        ThreadCounter threadCounter = new ThreadCounter("counter", 10);

        threadCounter.start();
    }
}

