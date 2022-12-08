package com.example.imdbproject.model.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PrincipalResponse {

    private NameBasicSummery nameBasicSummery;
    private String job;
    private String character;


    public PrincipalResponse(NameBasicSummery nameBasicSummery, String job, String character) {
        this.nameBasicSummery = nameBasicSummery;
        this.job = job;
        this.character = character;
    }
}
