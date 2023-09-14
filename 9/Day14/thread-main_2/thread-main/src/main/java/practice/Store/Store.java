package practice.Store;

import java.util.concurrent.Semaphore;


// 매장은 물건을 납품 받아서 판매한다.! -> sell

// 매장에는 최대 10개의 물건만 전시할 수 있다. -> maxCount

// 매장은 최대 5명까지만 동시 입장 가능하다.!

// 매장에서 물건 구매는 동시에 1명만 가능하다.! -> synchronized

// 매장에서 물건 판매 후 빈 공간에 생기면 생산자에게 알려 준다.!    -> sysout

// 매장에서 물건 납품은 동시에 1명만 가능하다.! -> synchronized

// 매장에서 물건이 들어오면 소비자에게 알려 준다.! -> sysout

public class Store {
    // int count;      // 손님 수
    int maxCount;   // 최대 입장 수
    Semaphore box;
    Semaphore things;
    Semaphore customer;

    public Store(int maxCount) {
        this.maxCount = maxCount;
        // count = 0;
        // 매장에는 최대 10개의 물건만 전시할 수 있다.
        box = new Semaphore(10);
        things = new Semaphore(0);

        // 고객 동시에 5명 가능
        customer = new Semaphore(5);
    }

    public void enter(String string) throws InterruptedException{
        try {            
            while (!customer.tryAcquire()) {
                wait();
            }
            // count++;
            System.out.println(string + " 손님 입장");
        } catch (IllegalMonitorStateException e) {
        }
    }

    public void exit(String string) {
        // 권한 회수

        customer.release();
        System.out.println(string + " 손님 퇴장");
    }

    public synchronized void buy() throws InterruptedException{    // 손님이 사갈때
        while(!things.tryAcquire()){
            wait();
        }
        box.release();
        //매장에서 물건이 들어오면 소비자에게 알려 준다.
        System.out.println("물건이 생겼습니다. 빈 매대 : " + box.availablePermits() + " 물건 갯수 : " + things.availablePermits());
    }

    public synchronized void sell() throws InterruptedException{   // 생산자가 팔때
        while (!box.tryAcquire()) { // 매대에 빈 공간이 남아있어? -> 없다!
            wait();
        }
        things.release();
        // 매장에서 물건 판매 후 빈 공간에 생기면 생산자에게 알려 준다.
        System.out.println("빈 공간이 생겼습니다. 빈 매대 : " + box.availablePermits()+ " 물건 갯수 : " + things.availablePermits());
    }
}
