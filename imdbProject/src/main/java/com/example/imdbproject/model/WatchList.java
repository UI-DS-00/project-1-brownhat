package com.example.imdbproject.model;


import com.example.imdbproject.model.response.FavouriteListResponse;
import com.example.imdbproject.model.response.WatchListResponse;
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
@Builder
public class WatchList {
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

}
