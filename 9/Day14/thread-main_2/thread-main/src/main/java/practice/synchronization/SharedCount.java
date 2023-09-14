package practice.synchronization;

public class SharedCount {
    int count;

    public SharedCount() {
        count = 0;
    }
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void increment() {
        count++;
        // setCount(getCount() + 1);
        try{
            Thread.sleep(100);  // 들어가서 지 할일하고 주어진 시간만큼 자는 거 
        } catch(InterruptedException e){
        }
    }

}   
