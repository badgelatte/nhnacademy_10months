package main;

public class Dice {
    private int number; // private - 캡슐화 지키기 위해 달기! && 자동화 땜시 getter setter 무조건 넣으면 캡슐 깨짐

    public Dice(int number){
        if (number <= 0 || number > 6) 
            throw new IllegalArgumentException("1 미만 6 초과 주사위는 생성할 수 없습니다.");
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
