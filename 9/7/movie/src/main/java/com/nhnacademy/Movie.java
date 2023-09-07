package com.nhnacademy;

import java.util.HashSet;
import java.util.Set;

public class Movie {
    private final long movieId;
    private final String title;
    private final Set<String> genres = new HashSet<String>();

    public Movie(long movieId, String title, String genres) {
        this.movieId = movieId;
        this.title = title;
        this.genres.add(genres);
    }

    // 나머지 필드에 접근할 수 있는 게터 메서드 (getter methods)를 구현할 수도 있습니다.
    public long getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public String getGenres() {
        return this.genres.stream().reduce((one, two)-> two).get();
    }

    public String toString() {
        return getMovieId() + "," + getTitle() + "," + getGenres();
    }
}