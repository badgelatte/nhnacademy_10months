package com.nhnacademy;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class ApacheCommonsCsvMovieParser implements MovieParser {

    @Override
    public List<Movie> parse(String fileName) throws IOException {
        CSVParser parser = new CSVParser(new InputStreamReader(getMovieFileAsStream()), CSVFormat.DEFAULT);
        long movieId=  0;
        String title = "";
        String genre = "";
        List<Movie> movies = new ArrayList<>();

        for( CSVRecord csvRecord : parser ) {
            if(csvRecord.get(0).equals("movieId") || csvRecord.get(0).equals("title") || csvRecord.get(0).equals(genre)){
            }
            else {
                movieId = Long.parseLong(csvRecord.get(0));
                title = csvRecord.get(1);
                genre = csvRecord.get(2);
                Movie movie = new Movie(movieId, title, genre);
                movies.add(movie);
            }
        }

        return movies;
    }
}
