package com.example.imdbproject.model.response;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
public class NameBasicSummery {


    String primaryName;
    Integer birthYear;
    Integer deathYear;


    public NameBasicSummery(String primaryName, Integer birthYear, Integer deathYear) {
        Integer deathYear1;
        this.primaryName = primaryName;
        this.birthYear = birthYear;
        deathYear1 = deathYear;

        if(deathYear1 == 0)
            deathYear1 = null;
        this.deathYear = deathYear1;
    }
}
