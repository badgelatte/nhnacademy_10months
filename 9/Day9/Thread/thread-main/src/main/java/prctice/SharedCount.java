package prctice;

public class SharedCount {
    int count;

    public SharedCount() {
        count = 0;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count){
        this.count = count;
    }

    public synchronized void increment() {  // -> synchronized를 붙이면 한 개가 들어가면 block, 즉 막는다. *waiting 상태는 아니고 block 상태인데 멈춰있다
            // interrupted 발생 조건 = waiting인데 synchrnized는 block이라 발생하지 않는다.
            // block 상태는 별로 좋지 않다는 것 주의!
        setCount(getCount() +1);
    }
}
