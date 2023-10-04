import java.util.concurrent.Semaphore;

public class Store {
    Semaphore box;
    int boxCount;
    public Store(int boxCount) {
        box = new Semaphore(boxCount);
    }

    public void enter() {
        System.out.println("소비자가 입장합니다.");
    }

    public void exit() {
        System.out.println("소비자가 퇴장합니다.");
    }

    public synchronized void buy() {
        System.out.println("소비자가 물건을 구매합니다.");
        box.release();  // 채워진 상자 비우기
        
    }

    public synchronized void sell() throws InterruptedException{
        while(!box.tryAcquire()){   // 빈상자가 없다면 -> !box.tryAcquire()
            wait();                 // 대기
        }
        if (!box.tryAcquire()) {
            throw new NotEnoughtBoxExcetion();
        }
        
        box.acquire(); // 빈상자 채우기
    }
}