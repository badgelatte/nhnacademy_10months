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
        setCount(getCount() + 1);
    }

}   
