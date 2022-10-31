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
class Crew {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;



    @OneToOne
    private TitleBasic tConst;

    @ManyToMany
    private Set<NameBasic> directors;


    @ManyToMany
    private Set<NameBasic> writers;

}