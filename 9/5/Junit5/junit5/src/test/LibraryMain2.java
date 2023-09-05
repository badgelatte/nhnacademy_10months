package test;

import main.*;

public class LibraryMain2 {
    private static final String NEGATIVE_LIBRARY_MESSAGE = "음수로는 도서관을 생성할 수 없습니다.";
    private static final String ADD_MORE_THAN_MAX_MESSAGE = "도서관 최대 용량을 초과해 책을 추가할 수 없습니다.";
    private static final String ADD_DUPLICATE_BOOK_MESSAGE = "도서관에 같은 이름의 책이 존재합니다.";
    private static final String DELETE_NOT_EXIST_BOOK_MESSAGE = "도서관에 존재하지 않는 책은 삭제할 수 없습니다.";

    public static void main(String[] args) {
        negativeLibrary();
        addBook();
        addMoreThanMax();
        duplicateBook();
        findBook();
        deleteBook();
        deleteNotExistBook();
    }

    private static void negativeLibrary() {
        try {
            new Library(-1);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals(NEGATIVE_LIBRARY_MESSAGE);
        }
    }

    private static void addBook() {
        Library library = new Library(5);

        library.add("해리포터");
        library.add("어린왕자");
        library.add("샬롯의 거미줄");

        assert 3 == library.getTotalBookCount();
    }

    private static void addMoreThanMax() {
        Library library = new Library(1);

        library.add("자바의 정석");
        
        try {
            library.add("모두의 리눅스");
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals(ADD_MORE_THAN_MAX_MESSAGE);
        }
    }

    private static void duplicateBook() {
        Library library = new Library(5);

        library.add("백설공주");
        
        try {
            library.add("백설공주");
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals(ADD_DUPLICATE_BOOK_MESSAGE);
        }
    }

    private static void findBook() {
        Library library = new Library(5);

        library.add("어린왕자");
        library.add("해리포터");
        library.add("샬롯의 거미줄");

        assert library.find("어린왕자");
        assert !library.find("엄지공주");
    }

    private static void deleteBook() {
        Library library = new Library(5);

        library.add("신데렐라");
        
        library.delete("신데렐라");
    }

    private static void deleteNotExistBook() {
        Library library = new Library(5);
        
        try {
            library.delete("흥부와 놀부");
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals(DELETE_NOT_EXIST_BOOK_MESSAGE);
        }
    }
}
