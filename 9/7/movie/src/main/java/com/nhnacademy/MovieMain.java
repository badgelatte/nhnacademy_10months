package com.nhnacademy;

import java.util.List;

public class MovieMain {

  public static void main(String[] args) {
    //MovieParser movieParser = new BasicMovieParser(); // Basic
    MovieParser movieParser = new ApacheCommonsCsvMovieParser();    // Apache

    try {
      List<Movie> movieList = movieParser.parse("movies.csv");
      for(Movie movie : movieList){
        System.out.println(movie);
      }
    }catch (Exception e){
      System.out.println(e.getMessage());
    // e.getMessage();
    }


  }
}
