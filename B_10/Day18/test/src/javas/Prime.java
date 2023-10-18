package javas;
public class Prime {
    public static boolean isPrime(int number){
        for (int i = 2; i < number; i++) {
            if(number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void getPrimeInRange(int start, int end) {
        if(start == 1){
            start = 2;
        }
        for (int i = start; i < end; i++) {
            if(isPrime(i)){
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        getPrimeInRange(1, 10);
    }
}
