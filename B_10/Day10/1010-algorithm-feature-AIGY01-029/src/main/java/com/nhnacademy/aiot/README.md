# 동시성 제어 - 1과 2의 반복

## [ 설명 ]

- [OneTwo.java](OneTwo.java)의 instance를 서로 다른 threadA, threadB에 전달합니다.
- threadA는 1을 출력합니다.
- threadB는 2를 출력합니다.

### [ 방법 1 ] java.util.concurrent.Semaphore 사용
### [ 방법 2 ] thread wait(), notify() 사용

```java
public class OneTwo {
    private final Semaphore s1 = new Semaphore(0);
    private final Semaphore s2 = new Semaphore(1);
    private final int count;

    public OneTwo(int count) {}

    public void printOne(Runnable runnable) throws InterruptedException {}

    public void printTwo(Runnable runnable) throws InterruptedException {}
}
```

## [ 문제 ( 다음과 같이 출력되도록 구현합니다. ) ]

- count = 1 이면
  - [출력] 12
- count = 2 이면
  - [출력] 1212
- count = 3 이면
  - [출력] 121212
- count = 4 이면
  - [출력] 12121212
- count = 5 이면
  - [출력] 1212121212

## [ 테스트 코드 ]

- [OneTwo.java](OneTwo.java) 테스트를 통과해야 합니다.
- mvn test