package com.example.imdbproject.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Table
@Data

public class AllUser
{
    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String password;


    //@OneToMany(mappedBy = "user")
    @OneToMany

    @JoinTable(
            name = "user_comment",
            joinColumns = @JoinColumn(name = "allUser"),
            inverseJoinColumns = @JoinColumn(name = "Comment"))
    private Set<Comment> comments;


    //@OneToMany(mappedBy = "owner")
    @OneToMany
    @JoinTable(
            name = "user_watchList",
            joinColumns = @JoinColumn(name = "allUser"),
            inverseJoinColumns = @JoinColumn(name = "watchList"))

    private Set<WatchList> movieLists;


    //@OneToMany(mappedBy = "owner")
    @OneToMany
    @JoinTable(
            name = "user_favourite",
            joinColumns = @JoinColumn(name = "allUser"),
            inverseJoinColumns = @JoinColumn(name = "FavouriteList"))

    private Set<FavouriteList> favoriteLists;

    @ManyToMany (fetch = FetchType.EAGER)
    private Set <Role> roles;

}
