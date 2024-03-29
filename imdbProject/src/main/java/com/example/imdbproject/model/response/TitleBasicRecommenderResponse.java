package com.example.imdbproject.model.response;

import com.example.imdbproject.model.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TitleBasicRecommenderResponse {

    private String filmName;
    private Set<String> genres = new HashSet<>();
    private Float rate;
    private int endYear;

}
