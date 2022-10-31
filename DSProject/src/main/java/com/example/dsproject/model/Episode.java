package com.example.dsproject.model;


import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Table

class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    //private TitleBasic tConst;

    //private TitleBasic parentConst;
    private int seasonNumber;
    private int episodeNumber;


}