package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import main.Dice;
import main.DiceCalculator;

@TestInstance(Lifecycle.PER_CLASS)  // 이거 써야지 한번에 class가 실행되도록 해준다
class DiceJunitTest {   // Junit으로 Test 짜보기
    private final String OUT_OF_RANGE_MESSAGE = "1 미만 6 초과 주사위는 생성할 수 없습니다.";
    private Dice first;
    private Dice second;

    @BeforeEach // 각 테스트 메소드가 실행되기 전 실행되어야 하는 메소드를 명시
    void setUp() {
        first = new Dice(6);
        second = new Dice (2);
    }
    
    @Test   // Test가 2개인데 junipter api 사용해야한다 
    @DisplayName("addDice 성공")    // 테스트 메서드에 대한 이름 선언
    void addDice() {
        assertEquals(DiceCalculator.addDice(first, second), 8);
        // assertEquals = 둘이 같은가?
    }

    @Test   // 
    @DisplayName("subDice 성공")
    void subDice() {
        assertEquals(DiceCalculator.subDice(first, second), 4);
    }

    @Test
    @DisplayName("mulDice 성공")
    void mulDice() {
        assertEquals(DiceCalculator.mulDice(first, second), 12);
    }

    @Test
    @DisplayName("divDice 성공")
    void divDice() {
        assertEquals(main.DiceCalculator.divDice(first, second), 3);
    }

    @Test
    @DisplayName("음수 값의 주사위를 생성할 수 없을 때, IllegalArgumentException을 던짐.")
    void negativeDice_throwIllegalArgumentException() {
        // IllegalArgumentException - 메서드가 허가 되지 않거나 부적절한 argument를 받았을 경우에 던질 수 있는 예외
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Dice(-1));
        // assertThrows - 2번째 인자를 실행했을 때 1번째 인자가 포함되는가
        // IllegalArgumentException.class -> IllegalArgumentException는 클래스이기 때문에 .class를 붙인다
        // () -> new Dice(-1) = Dice 생성자에 -1 넣는 것으로 lamda(람다식)으로 작성됨.
        assertEquals(exception.getMessage(), OUT_OF_RANGE_MESSAGE);
        // Dice.java의 생성자 속 " " 안의 문구가 달라지면 에러가 뜬다
    }

    @AfterAll   // 테스트가 완전히 끝난 후 딱 한 번만 실행
    void done() {
        System.out.println("done");
    }
}
