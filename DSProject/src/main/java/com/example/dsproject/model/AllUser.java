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
public class AllUser
{
    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String password;

    @OneToMany
    private Set<Comment> comments;


    @OneToMany
    private Set<WatchList> watchLists;


    @OneToMany
    private Set <TitleBasic> favoriteLists;


}
