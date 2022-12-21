package com.example.imdbproject.model;


import com.example.imdbproject.model.response.FavouriteListResponse;
import com.example.imdbproject.model.response.TitleBasicFavouriteList;
import com.example.imdbproject.model.response.TitleBasicWatchList;
import com.example.imdbproject.model.response.WatchListResponse;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Table
@Builder
public class WatchList implements Comparable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column (unique = false)
    private String name;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(
            name = "owner_id"
    )
    private AllUser owner;


    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "titleBasics")
    private TitleBasic titleBasic;


    public WatchList( String name, AllUser owner) {
        this.name = name;
        this.owner = owner;

    }

    public WatchListResponse toResponse(WatchList watchList){
        WatchListResponse watchListResponse = new WatchListResponse();
        watchListResponse.setTitleBasicResponse(watchList.getTitleBasic().responseModel());
        watchListResponse.setName(watchList.getName());
        return watchListResponse;
    }

    public static TitleBasicWatchList showList (Set<WatchList> watchLists){

        TitleBasicWatchList titleBasicWatchList = new TitleBasicWatchList();
        for (WatchList watchList : watchLists) {
            titleBasicWatchList.getMovieName().add(watchList.getTitleBasic().getPrimaryTitle());
            titleBasicWatchList.setName(watchList.getName());
            titleBasicWatchList.setOwnerUsername(watchList.getOwner().getUsername());

        }
        return titleBasicWatchList;
    }
    public int compareTo(Object o1) {

        WatchList watchList = (WatchList) o1;
        return (this.getName().compareTo(watchList.getName()));
    }

}
