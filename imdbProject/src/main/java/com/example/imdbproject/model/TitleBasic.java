package com.example.imdbproject.model;


import com.example.imdbproject.model.response.TitleBasicResponse;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;


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
    private String genres;


    public TitleBasicResponse responseModel(){
        return new TitleBasicResponse(titleType,primaryTitle,originalTitle
                ,isAdult,startYear,endYear,runtime,genres);
    }

}