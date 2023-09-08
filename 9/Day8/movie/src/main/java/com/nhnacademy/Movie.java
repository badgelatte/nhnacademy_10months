package com.nhnacademy;

import java.util.Set;

public class Movie {
  private final long movieId;
  private final String title;
  private final Set<String> genres;

  public Movie(long movieId, String title, Set<String> genres){
    this.movieId = movieId;
    this.title = title;
    this.genres = genres;
  }

  public long getMovieId() {
    return this.movieId;
  }

  public String getTitle() {
    return this.title;
  }

  public Set<String> getGenres() {
    return this.genres;
  }

  @Override
  public String toString() {
    return getMovieId() + " " + getTitle() + " " + getGenres();
  }
}

