package com.example.imdbproject.model;


import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Table
public
class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


    @OneToOne
    @JoinColumn(name = "t_const_tconst")
    private TitleBasic tConst;

    private float averageRate;
    private int vote_numbers;


   }