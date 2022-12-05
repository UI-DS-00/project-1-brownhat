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
public class PrimaryProfession {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @ManyToOne
//    @JoinColumn(name = "name_basic_primary_professions")
    private NameBasic nameBasic;

    private String profession;

}
