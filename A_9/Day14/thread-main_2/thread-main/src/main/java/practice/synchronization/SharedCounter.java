package practice.synchronization;

public class SharedCounter extends Thread{
    int count;
    int maxCount;
    SharedCount sharedCount;

    public SharedCounter(String name, int maxCount, SharedCount sharedCount) {
        setName(name);
        count = 0;
        this.maxCount = maxCount;
        this.sharedCount = sharedCount;
    }

    public void run() {
        while(count < maxCount) {
                count++;
                synchronized(sharedCount){  // 보호해야할 object를 매개변수(파라미터) 안에 넣는다.
                    sharedCount.increment();

                }
                
            }
    }


}
