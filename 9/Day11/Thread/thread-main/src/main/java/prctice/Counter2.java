package prctice;

public class Counter2 {
    int count;  // 여기서 초기화 해도 된다
    String name;
    int maxCount;

    public Counter2(String name, int maxCount) {
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
        Counter2 counter1 = new Counter2("counter", 5);
        Counter2 counter2 = new Counter2("counter", 5);
        
        counter1.run();
        counter2.run();

        System.out.println("Finish");   // 일반적으로는 다 돌고 sysout이 찍힘
    }
}
