package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.Dice;
import main.DiceCalculator;

class DiceJunitTest {
    @Test   // Test가 2개인데 junipter api 사용해야한다 
    @DisplayName("addDice 성공")
    void addDice() {
        Dice first = new Dice(6);
        Dice second = new Dice (2);

        assertEquals(DiceCalculator.addDice(first, second), 8);
    }


    @Test
    @DisplayName("음수 값의 주사위를 생성할 수 없을 때, IllegalArgumentException을 던짐.")
    void negativeDice_throwIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Dice(-1));
        // ...?
        assertEquals(exception.getMessage(), "1 미만 6 초과 주사위는 생성할 수 없습니다.");
        // Dice.java의 생성자 속 " " 안의 문구가 달라지면 에러가 뜬다
    }
}
