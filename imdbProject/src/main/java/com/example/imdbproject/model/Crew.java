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
public
class Crew {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;




    @OneToOne
    @JoinColumn(name = "t_const_tconst")
    private TitleBasic tConst;

    @ManyToMany
    @JoinTable(
            name = "Crew_NameBasic_Directors",
            joinColumns = @JoinColumn(name = "NameBasic_id"),
            inverseJoinColumns = @JoinColumn(name = "Crew_id"))
    private Set<NameBasic> directors;


    @ManyToMany
    @JoinTable(
            name = "Crew_NameBasic_Writers",
            joinColumns = @JoinColumn(name = "NameBasic_id"),
            inverseJoinColumns = @JoinColumn(name = "Crew_id"))
    private Set<NameBasic> writers;

}