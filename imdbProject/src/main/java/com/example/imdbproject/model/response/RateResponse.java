package com.example.imdbproject.model.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class RateResponse {
    private Float rate;
    private Integer voteNum;
}
