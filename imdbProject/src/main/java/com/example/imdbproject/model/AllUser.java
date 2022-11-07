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
public class AllUser
{
    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<Comment> comments;


    @OneToMany(mappedBy = "owner")
    private Set<WatchList> watchLists;


    @OneToMany(mappedBy = "tConst")
    private Set<TitleBasic> favoriteLists;


}
