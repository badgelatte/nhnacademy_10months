// Quiz-03. Counter class의 instance를 2개 이상 만들고 실행 후 동작에 대해 설명해보자

public class thread_2_Counter2{
    String name;
    int maxCount;
    int count;

// 시작 후 1초마다 횟수를 1씩 증가 시킨다.

// 횟수가 변경될 때마다 이름과 횟수를 출력한다.

// 주어진 최대 횟수에 도달하면, 동작을 종료한다.
    
    public thread_2_Counter2(String name, int maxCount) {
        this.name = name;
        this.maxCount = maxCount;
        count = 0;    
    }

    
    public void run() {
        try {
            while(count < maxCount) {
                count++;
                System.out.println(name +" : " + count);
                Thread.sleep(1000);
            }
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
    }

    public static void main(String[] args) {
        thread_2_Counter2 counter1 = new thread_2_Counter2("counter", 5);
        thread_2_Counter2 counter2 = new thread_2_Counter2("counter", 5);
        counter1.run();
        counter2.run();
    }
}
