// Quiz-01. RunnableCounter class에 중지를 위한 field와 method를 추가하여 동작을 확인하라.
//...? 다ㅏㅏㅏㅏ시 해보자...
public class stop_RunningFlag implements Runnable {
    String name;
    int maxCount;
    int count;
    boolean runningFlag;

    public stop_RunningFlag(String name, int maxCount) {
        this.name = name;
        this.maxCount = maxCount;
        count = 0;
    }
    public void start(){
        runningFlag = true;
    }

    public void stop() {
        runningFlag = false;
    }
    public void run() {
        try {
            start();
            // runningFlag = true가 true이고 count < maxCount도 true이어야 true로 반환한다
            // and연산자는 좌항과 우황이 모두 '참' 일 때만 전체가 참이 됩니다.
            // or연산자는 좌항 우황 중 하나라도 참이면 전체가 참이 되어 값이 출력하게 됩니다.
            while(runningFlag && (count < maxCount)) {
                count++;
                System.out.println(name +" : " + count);
                Thread.sleep(1000);
            }
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
    }

    public static void main(String[] args) {
        stop_RunningFlag stopRunningFlag = new stop_RunningFlag("runningFlag", 5);
        // Thread thread = new Thread(stopRunningFlag);
        stopRunningFlag.run();
        stopRunningFlag.stop();
    }
}