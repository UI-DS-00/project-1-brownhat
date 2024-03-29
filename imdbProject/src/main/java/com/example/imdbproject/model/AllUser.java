package com.example.imdbproject.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@ToString
@Getter
@Setter
@Table
@Data

public class AllUser
{

    @Transient
    public static AllUser user = null;

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique=true, nullable=false)
    private String username;
    private String password;

    @OneToMany
    private Set<TitleBasic> ratingFilms;

    public AllUser( String username, String password) {
        this.username = username;
        this.password = password;
        this.comments = new HashSet<>();
        this.watchLists = new HashSet<>();
        this.favoriteLists = new HashSet<>();
        this.roles = new HashSet<>();
    }

    //@OneToMany(mappedBy = "user")
    @OneToMany()

    @JoinTable(
            name = "user_comment",
            joinColumns = @JoinColumn(name = "allUser"),
            inverseJoinColumns = @JoinColumn(name = "Comment"))
    private Set<Comment> comments;


    //@OneToMany(mappedBy = "owner")
    @OneToMany(mappedBy = "owner")
    @ToString.Exclude
    private Set<WatchList> watchLists;


    //@OneToMany(mappedBy = "owner")
    @OneToMany(mappedBy = "owner")
    @ToString.Exclude
    private Set<FavouriteList> favoriteLists;

    @ManyToMany (fetch = FetchType.EAGER)
    private Set <Role> roles;

}
