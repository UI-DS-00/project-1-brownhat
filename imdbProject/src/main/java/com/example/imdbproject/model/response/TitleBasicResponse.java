package com.example.imdbproject.model.response;

import com.example.imdbproject.model.Genre;
import lombok.Value;

import java.util.Set;

@Value
public class TitleBasicResponse {


    String titleType;
    String primaryTitle;
    String originalTitle;
    boolean isAdult;
    int startYear;
    int endYear;
    int runtime;
    Set<Genre> genres;
    Set<String> allGenres;

}
