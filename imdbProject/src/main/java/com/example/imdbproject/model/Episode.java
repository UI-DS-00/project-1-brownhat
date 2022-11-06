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
public
class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "t_const_id")
    private TitleBasic tConst;

    @ManyToOne
    @JoinColumn(name = "parent_const_id")
    private TitleBasic parentConst;
    private int seasonNumber;
    private int episodeNumber;


}