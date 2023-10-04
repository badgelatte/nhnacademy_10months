package prctice;

public class Counter {
    int count;  // 여기서 초기화 해도 된다
    String name;
    int maxCount;

    public Counter(String name, int maxCount) {
        this.name = name;
        this.maxCount = maxCount;
        count = 0;      // 모든게 0으로 초기화 된다
    }

    public void run() {
        try {
            while(count < maxCount) {
                count++;
                System.out.println(name + " : " + count);
                Thread.sleep(1000);     // 1초동안 쉰다
            }

        } catch (InterruptedException e) {  // Exception을 새로 던져주던지 ~~~하던지
            // throw new InterruptedException();
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        Counter counter = new Counter("counter", 5);
        counter.run();

        System.out.println("Finish");   // 일반적으로는 다 돌고 sysout이 찍힘
    }
}
