package prctice;

public class RunnableCounter_Exam02_2 implements Runnable{ // made by teacher
    int count;  // 여기서 초기화 해도 된다
    String name;
    int maxCount;
    Thread thread;

    public RunnableCounter_Exam02_2(String name, int maxCount) {
        this.name = name;
        this.maxCount = maxCount;
        count = 0;      // 모든게 0으로 초기화 된다
        this.thread = new Thread(this);
    }

    public void start() {
        thread.start();
    }

    public void run() {
        try {
            while(count < maxCount) {
                count++;
                System.out.println(name + " : " + count);
                Thread.sleep(1000);     // 1초동안 쉰다
            }

        } catch (InterruptedException e) {  // Exception을 새로 던져주던지 
                                            //그 스레드가 interrupt가 떴다가고 전달해주던가(-> 이건 우리가 직접 지정해야한다) 이렇게 2가지 한다
            // throw new InterruptedException();    // 밑줄이나 이 줄이나 둘 중 하나 골라 쓸 수 있는데 주로 밑줄을 더 많이 사용한다.
            Thread.currentThread().interrupt();
        }
    }
    public Thread getThread() {
        return thread;
    }

    // single thread -> 베일이 모든 스레드 돌림 
    // start의 코드는 main이 돌린다. t1이 돌리는게 아니다 thread를 extends한 것뿐이다 -> thread는 run()을 실행할 뿐
    // 어떠한 작업을 할 수 있도록 만들고 thread가 그 작업을 run할 수 있도록 해줌
    // thread는 run안에서 실행하는 코드만 한다
    public static void main(String[] args) {
        RunnableCounter_Exam02_2 counter1 = new RunnableCounter_Exam02_2("counter", 5);
        RunnableCounter_Exam02_2 counter2 = new RunnableCounter_Exam02_2("counter", 5);

        counter1.getThread();
        
        counter1.start();
        counter2.start();

        System.out.println("Finish");   // 일반적으로는 다 돌고 sysout이 찍힘
    }
}
