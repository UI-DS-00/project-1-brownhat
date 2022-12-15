package com.example.imdbproject.model.response;

import com.example.imdbproject.model.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
public class TitleBasicResponse {


    private String titleType;
    private String primaryTitle;
    private String originalTitle;
    private boolean isAdult;
    private int startYear;
    private int endYear;
    private int runtime;
    private Set <String> genres;

    private Set <PrincipalResponse> actors; //casts
    private Set <PrincipalResponse> crew;
    private Set <CommentResponse> comments;

    private RateResponse rate;



    public TitleBasicResponse(String titleType, String primaryTitle, String originalTitle, boolean isAdult, int startYear, int endYear, int runtime
            , Set<String> genres) {
        this.titleType = titleType;
        this.primaryTitle = primaryTitle;
        this.originalTitle = originalTitle;
        this.isAdult = isAdult;
        this.startYear = startYear;
        this.endYear = endYear;
        this.runtime = runtime;
        this.genres = genres;
    }
}
