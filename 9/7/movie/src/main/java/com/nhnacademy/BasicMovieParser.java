package com.nhnacademy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BasicMovieParser implements MovieParser {
    // private BufferedReader movieList;
  @Override
  public List<Movie> parse(String fileName) throws IOException {
    BufferedReader movieList = new BufferedReader(new FileReader("src/main/java/com/nhnacademy/resources/movies.csv"));

    String data = "";
    String [] slist;
    long movieId = 0;
    String title= "";
    String genre= "";

    List<Movie> movies = new ArrayList<>();
    try {
        while((data = movieList.readLine()) != null){
            
            if(data.equals("movieId,title,genres")) {
            }
            else {
                slist = data.split(",");
                movieId = Long.parseLong(slist[0]);
                title = slist[1];
                genre = slist[2];
                Movie movie = new Movie(movieId, title, genre);
    
                movies.add(movie);
    
            }
    
        }

    } catch(IOException e){
        e.printStackTrace();
    }
    return movies;
  }

}