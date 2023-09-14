package practice.Store;


public class PCSolution1 {
    public static void main(String[] args) throws InterruptedException{
        Store store = new Store(10);
        Producer producer = new Producer(store);
        producer.start();

        for(int i = 1; i <=15; i++) {
            // try{
                Consumer consumer = new Consumer("C" + i, store);
                Thread.sleep(1000);
                consumer.start();
            // } catch (IllegalAccessException e) {
            //     // consumer.wait();
            // }
        }
        
    }

}
