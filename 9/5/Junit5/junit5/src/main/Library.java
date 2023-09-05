package main;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private int maxBookCount;
    private List<String> booklist;
    
    public Library(int count) {
        if(maxBookCount < 0)
            throw new IllegalArgumentException("음수로는 도서관을 생성할 수 없습니다.");
        this.maxBookCount = count;
        this.booklist = new ArrayList<>();
    }
    
    public void add(String bookName) {
        if(maxBookCount < getTotalBookCount()){
            System.out.println("도서관 최대 용량을 초과해 책을 추가할 수 없습니다.");
        }
        else {
            booklist.add(bookName);
            System.out.println("추가한 책 이름: " + bookName);
        }
    }

    public int getTotalBookCount() {
        System.out.println("최대 책 갯수: " + booklist.size());
        return booklist.size();
    }

    public boolean find(String bookName) {
        for(String a: booklist) {
            if(a == bookName){
                System.out.println("도서관에 같은 이름의 책이 존재합니다.");
                return true;
            }
            //System.out.println("");
        }
        return false;   // foreach문을 안 돌았을 경우는 이 return을 내놓는다 -> = 책이 없다는 뜻
    }

    public void delete(String bookName) {
        if(booklist.contains(bookName)){
            booklist.remove(bookName);
            System.out.println(bookName + "을 삭제합니다.");
        }
        else 
            System.out.println("도서관에 존재하지 않는 책은 삭제할 수 없습니다.");
    }
}
