package com.example.imdbproject.model;


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
public class WatchList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column (unique = true)
    private String name;

    @ManyToOne
    private AllUser owner;

    @OneToMany(mappedBy = "tConst")
    @JoinColumn(name = "titleBasics")
    private Set<TitleBasic> list;


    public WatchList( String name, AllUser owner, Set<TitleBasic> list) {
        this.name = name;
        this.owner = owner;
        this.list = list;
    }
}
