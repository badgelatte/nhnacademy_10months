public class Counter {
    String name;
    int count = 0;
    int maxCount = 10;

    public Counter(String name, int maxCount) {
        this.name = name;
        this.maxCount = maxCount;
    }

    public int getCount() {
        return count;
    }

    public int getMaxCount() {
        return maxCount;
    }
    public void CountPlus() {
        count++;
    }
    
    public void run(){
        try {
            while(count <= maxCount) {
                count++;
                System.out.println(name + " : " + count);
                Thread.sleep(1000);
            }            
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }


    public static void main(String[] args) {
        Counter counter = new Counter("counter", 10);
        counter.run();
    }
}
