package com.example.imdbproject.model.response;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
public class NameBasicSummery {


    String primaryName;
    int birthYear;
    int deathYear;

}
