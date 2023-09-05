package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import main.DiceCalculator;
import main.Library;

@TestInstance(Lifecycle.PER_CLASS)
public class LibraryJunitTest {
    private static final String NEGATIVE_LIBRARY_MESSAGE = "음수로는 도서관을 생성할 수 없습니다.";
    private static final String ADD_MORE_THAN_MAX_MESSAGE = "도서관 최대 용량을 초과해 책을 추가할 수 없습니다.";
    private static final String ADD_DUPLICATE_BOOK_MESSAGE = "도서관에 같은 이름의 책이 존재합니다.";
    private static final String DELETE_NOT_EXIST_BOOK_MESSAGE = "도서관에 존재하지 않는 책은 삭제할 수 없습니다.";
    

    @Test
    @DisplayName("음수 값의 도서관을 만들때, IllegalArgumentException 던짐")
    void negativeLibrary_throwIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Library(-1));
        assertEquals(exception.getMessage(), NEGATIVE_LIBRARY_MESSAGE);
    }
    @Test
    @DisplayName("같은 이름의 책 도서관에서 삭제")
    void delete() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> );
        assertEquals(exception.getMessage(), NEGATIVE_LIBRARY_MESSAGE);
    }

    @ParameterizedTest
    @ValueSource(strings = { "어린왕자", "엄지공주" })  // strings -> string만 넣을 수 있다
    void add_success(String bookName) {
        Library library = new Library(5);

        library.add(bookName);
    }

    @RepeatedTest(value = 10)   //10번 반복
    void repeat() {
        System.out.println("dfdf");
    }
}
