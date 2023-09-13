package prctice;

public class RunnableThread_imade_fail extends Thread implements Runnable {
    int count = 0;  // 모든게 0으로 초기화 된다
    String name;
    int maxCount;

    public RunnableThread_imade_fail(String name, int maxCount) {
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
            System.out.println("maxCount : "+maxCount);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        RunnableThread_imade_fail runnableCounter1 = new RunnableThread_imade_fail("runnableCounter", 5);
        RunnableThread_imade_fail runnableCounter2 = new RunnableThread_imade_fail("runnableCounter", 5);

        
        runnableCounter1.start();  // thread는 먼저 sysout을 찍고 counter를 돈다 -> 더 자세히 보려면 debug를 찍어라 대신 수를 엄청크게 해라
                                // main이 먼저 끝나고 thread가 계속 돈다
        runnableCounter2.start();
    }
}