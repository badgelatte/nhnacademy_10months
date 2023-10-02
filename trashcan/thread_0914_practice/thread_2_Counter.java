public class thread_2_Counter{
    String name;
    int maxCount;
    int count;

// 시작 후 1초마다 횟수를 1씩 증가 시킨다.

// 횟수가 변경될 때마다 이름과 횟수를 출력한다.

// 주어진 최대 횟수에 도달하면, 동작을 종료한다.

// Quiz-01. 1초마다 횟수를 1씩 증가하는 Counter class로 정의하고, 실행 후 결과를 확인하라.


    public thread_2_Counter(String name, int maxCount) {
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
        thread_2_Counter counter = new thread_2_Counter("counter", 5);
        counter.run();
    }
}
