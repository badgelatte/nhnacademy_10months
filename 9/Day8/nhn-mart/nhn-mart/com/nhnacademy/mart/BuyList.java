package com.nhnacademy.mart;

import java.util.ArrayList;

public class BuyList {

    private final ArrayList<Item> items = new ArrayList<>();

    // TODO 07 터미널에서 입력받은 구매 목록을 리스트에 추가하는 메서드를 작성하세요
    public void buyList() {
        
    }

    // TODO 08 구매를 희망하는 물건들의 리스트를 반환하는 메서드를 작성하세요

    // 구매를 희망하는 물건 이름과 갯수
    public static class Item {
        private final String name;
        private final int amount;

        public Item(String name, int amount) {
            this.name = name;
            this.amount = amount;
        }

        public String getName() {
            return this.name;
        }

        public int getAmount() {
            return this.amount;
        }

    }

}
