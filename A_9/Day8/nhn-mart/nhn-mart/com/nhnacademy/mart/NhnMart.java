package com.nhnacademy.mart;

public class NhnMart {

    private final FoodStand foodStand = new FoodStand();
    private final Counter counter = new Counter();

    public void prepareMart() {
        this.fillFoodStand();
    }

    // 음식 세팅
    private void fillFoodStand() {
        for (int i = 0; i < 2; i++) {
            // this.foodStand.add(new Food("양파", 1_000));
        }
        for (int i = 0; i < 5; i++) {
            // this.foodStand.add(new Food("계란", 5_000));
        }
        for (int i = 0; i < 10; i++) {
            // this.foodStand.add(new Food("파", 500));
        }
        for (int i = 0; i < 20; i++) {
            // this.foodStand.add(new Food("사과", 2_000));
        }
    }

    public Basket provideBasket() {
        return new Basket();
    }

    public FoodStand getFoodStand() {
        return this.foodStand;
    }

    public Counter getCounter() {
        return this.counter;
    }

    
}
