package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.Dice;
import main.DiceCalculator;

class DiceJunitTest {   // Junit으로 Test 짜보기
    private Dice first;
    private Dice second;

    @BeforeEach
    void setUp() {
        first = new Dice(6);
        second = new Dice (2);
    }
    
    @Test   // Test가 2개인데 junipter api 사용해야한다 
    @DisplayName("addDice 성공")
    void addDice() {
        assertEquals(DiceCalculator.addDice(first, second), 8);
        // assertEquals = 둘이 같은가?
    }

    @Test
    @DisplayName("subDice 성공")
    void subDice() {
        assertEquals(DiceCalculator.subDice(first, second), 4);
    }

    @Test
    @DisplayName("음수 값의 주사위를 생성할 수 없을 때, IllegalArgumentException을 던짐.")
    void negativeDice_throwIllegalArgumentException() {
        // IllegalArgumentException - 메서드가 허가 되지 않거나 부적절한 argument를 받았을 경우에 던질 수 있는 예외
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Dice(-1));
        // ...?
        assertEquals(exception.getMessage(), "1 미만 6 초과 주사위는 생성할 수 없습니다.");
        // Dice.java의 생성자 속 " " 안의 문구가 달라지면 에러가 뜬다
    }
}
