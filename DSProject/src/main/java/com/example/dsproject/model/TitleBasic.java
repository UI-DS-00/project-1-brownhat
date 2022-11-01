package com.example.dsproject.model;

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


    private String title_type;
    private String primary_title;
    private String original_title;
    private boolean is_adult;
    private int start_year;
    private int end_year;
    private int runtime;
    private String genres;

}