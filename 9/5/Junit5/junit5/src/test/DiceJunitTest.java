package test;

import static org.junit.Assert.assertEquals;

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
}
