package com.example.imdbproject.model;


import com.example.imdbproject.model.response.TitleBasicResponse;
import lombok.*;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table
@ToString
public
class TitleBasic {

    @Id
    @Column(name = "tconst", nullable = false)
    private String tConst;


    private String title_type;
    private String primary_title;
    private String original_title;
    private boolean is_adult;
    private int start_year;
    private Integer endYear;
    private int runtime;
    private String genres;


    public TitleBasicResponse responseModel(){
        return new TitleBasicResponse(title_type,primary_title,original_title
                ,is_adult,start_year, endYear,runtime,genres);
    }

    public TitleBasic(String tConst){
        this.tConst=tConst;
    }

}