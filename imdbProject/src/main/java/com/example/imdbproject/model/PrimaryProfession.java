package com.example.imdbproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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


    @ManyToOne()
    @ToString.Exclude
    @JsonIgnore
    private NameBasic nameBasic;
    private String profession;

}
