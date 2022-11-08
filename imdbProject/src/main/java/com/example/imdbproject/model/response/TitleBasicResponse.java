package com.example.imdbproject.model.response;

import lombok.Value;

@Value
public class TitleBasicResponse {


    String titleType;
    String primaryTitle;
    String originalTitle;
    boolean isAdult;
    int startYear;
    int endYear;
    int runtime;
    String genres;


}
