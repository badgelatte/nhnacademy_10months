package prctice;

public class ThreadCounter extends Thread {
    int count = 0;  // 모든게 0으로 초기화 된다
    String name;
    int maxCount;

    public ThreadCounter(String name, int maxCount) {
        super(name);
        this.maxCount = maxCount;
    }

    @Override   // super class(Thread)에서 상속받아서 덮어쓰기 때문
    public void run() {
        try {
            while(count < maxCount) {
                count++;
                System.out.println(getName() + " : " + count);  // -> name으로 직접적으로 부르기 보단 getName을 통해 불러오는게 좋다
                Thread.sleep(1000);     // 1초동안 쉰다
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        ThreadCounter threadCounter = new ThreadCounter("counter", 5);

        threadCounter.start();  // thread는 먼저 sysout을 찍고 counter를 돈다 -> 더 자세히 보려면 debug를 찍어라 대신 수를 엄청크게 해라
                                // main이 먼저 끝나고 thread가 계속 돈다
        
        System.out.println("Finish");
    }
}

