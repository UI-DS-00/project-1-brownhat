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
class Principal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    //@JoinColumn(name = "t_const_id")
    private TitleBasic tConst;

    private int ordering;
    @ManyToOne
    //@JoinColumn(name = "n_const_id")
    private NameBasic nConst;

    private String category;
    private String job;
    private String characters;

}