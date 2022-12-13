package com.example.imdbproject.model;


import com.example.imdbproject.model.response.TitleBasicResponse;
import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table
@ToString
public
class TitleBasic {


    /*
        @Id
        @Column(name = "id", nullable = false)
        private Long id;
     */
    @Id
    @Column(name = "tconst", nullable = false)
    private String tConst;



    @OneToOne
    private Rating rating;

    private String titleType;

    private String primaryTitle;

    private String originalTitle;

    private boolean isAdult;

    private int startYear;

    private int endYear;
    private int runtime;
    @OneToMany
    //@JoinColumn(name = "genre")
    private Set<Genre> genres;

    @ElementCollection
    Set<String> allGenres;
    public TitleBasicResponse responseModel(){
        return new TitleBasicResponse(titleType,primaryTitle,originalTitle
                ,isAdult,startYear,endYear,runtime, genres,allGenres);
    }
    public TitleBasic(String tConst){
        this.tConst=tConst;
    }


    public TitleBasic(String tConst, String titleType, String primaryTitle, String originalTitle,
                      boolean isAdult, int startYear, int endYear, int runtime, Set<Genre> genres) {
        this.tConst = tConst;
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