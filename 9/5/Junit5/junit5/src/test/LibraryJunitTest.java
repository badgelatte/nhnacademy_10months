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
    private static Library library;

    @BeforeEach
    void setUp() {
       library = new Library(2);
    }

    @Test
    @DisplayName("음수 값의 도서관을 만들때, IllegalArgumentException 던짐")
    void negativeLibrary_throwIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Library(-1));
        assertEquals(exception.getMessage(), NEGATIVE_LIBRARY_MESSAGE);
    }
    
    @ParameterizedTest
    @ValueSource(strings = { "어린왕자", "엄지공주" })  // strings -> string만 넣을 수 있다
    @DisplayName("도서관에 책 넣기")
    void add_success(String bookName) {
        
        library.add(bookName);
    }

    @Test
    @DisplayName("도서관 책 총 갯수")
    void getTotalBookCount() {
        library.getTotalBookCount();
    }

    /* @Test
    @DisplayName("도서관 최대 보관 수보다 많은가")
    void addMoreThanMax_throwIllegalArgumentException() {
        // int count =library.getTotalBookCount() + 1;
        // 기존에 가진 library 속 maxBook에서 +1 해서 비교할라했다가 포기.. -> 아니 애초에 할 수 있는거야??
        Exception exception = assertThrows(IllegalArgumentException.class, () -> library.getTotalBookCount());
        assertEquals(exception.getMessage(), NEGATIVE_LIBRARY_MESSAGE);
    } */

    @ParameterizedTest
    @ValueSource(strings = { "어린왕자"})
    @DisplayName("도서관 책 찾기")
    void find_success(String bookName) {
        library.find(bookName);
    }
    
    @ParameterizedTest
    @ValueSource(strings = { "어린왕자", "엄지공주", "흥부와 놀부" })
    @DisplayName("도서관에서 같은 이름의 책 삭제")
    void delete_success_throwIllegalArgumentException(String bookName) {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->  library.delete(bookName));
        
        assertEquals(exception.getMessage(), DELETE_NOT_EXIST_BOOK_MESSAGE);
    }
    

    /* @RepeatedTest(value = 10)   //10번 반복
    void repeat() {
        System.out.println("dfdf");
    } */
}
