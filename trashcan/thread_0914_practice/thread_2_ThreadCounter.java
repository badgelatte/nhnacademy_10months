// Quiz-02. Thread class를 이용해 1초마타 횟수를 1씩 증가하는 ThreadCounter class를 정의하고, 실행 후 결과를 확인하라.

public class thread_2_ThreadCounter extends Thread {
    String name;
    int maxCount;
    int count;

    // 이름과 최대 횟수를 인수로 받아 초기화하다.
    // 시작 횟수는 0부터한다.
    public thread_2_ThreadCounter(String name, int maxCount) {
        setName(name);
        this.maxCount = maxCount;
        count = 0;    
    }

    
    public void run() {
        // 주어진 최대 횟수에 도달하면, 동작을 종료한다.
        while(count < maxCount) {
            try {
                count++;
                // 횟수가 변경될 때마다 이름과 횟수를 출력한다.
                System.out.println(getName() + " : " + count);
                // 시작 후 1초마다 횟수를 1씩 증가 시킨다.
                Thread.sleep(1000);
                
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
    }

    public static void main(String[] args) {
        thread_2_ThreadCounter threadCounter = new thread_2_ThreadCounter("threadCounter", 5);
        threadCounter.start();
    }
}
