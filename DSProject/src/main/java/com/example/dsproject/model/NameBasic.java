package com.example.dsproject.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Table
class NameBasic{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nConst;
    private long personId;
    private String primaryName;
    private int birth_year;
    private int death_year;


    private String primary_professions;

    @ManyToMany
    //@JoinColumn(name = "n_const_id")
    private Set<TitleBasic> KnownForTitles;

}