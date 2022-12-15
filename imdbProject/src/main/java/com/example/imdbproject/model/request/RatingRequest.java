package com.example.imdbproject.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RatingRequest {

    private String filmTConst;
    private Float amountOfRating;
}
