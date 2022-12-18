package com.example.imdbproject.model.response;

import com.example.imdbproject.model.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
public class TitleBasicRecommenderResponse {

    private String filmName;
    private Set<Genre> genres;

}
