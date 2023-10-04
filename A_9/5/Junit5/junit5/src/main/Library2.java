package main;

import java.util.ArrayList;
import java.util.List;

public class Library2 {
    String array[] = {};
    //List list;

    public Library2(int number){
        if(number > 0)
            array = new String[number];
        //list = new ArrayList<>();
    }

    public void add(String bookname) {
        for(int i = 0; i < array.length; i++) {
            if(array[i] == null) {
                array[i] = bookname;
            }
        }
        //list.add(bookname);
    }

    public int getTotalBookCount() {
        int count = 0;
        for(int i = 0; i< array.length; i++) {
            if( array[i] == null){
                return count;
            }
            count++;
        }
        return count;
    }

    public boolean find(String bookname) {
        boolean findexist = true;
        for(int i = 0; i < array.length; i++){
            if(array[i] == bookname) {
                //findexist = true;
                return true;
            }
            else{
                findexist = false;
            }
        }
        /* for(int book : list) {

        } */
        return findexist;
    }

    public void delete(String bookname) {
        for(int i = 0; i < array.length; i++){
            if(array[i] == bookname) {
                array[i] = null;
            }
        }
    }

}
