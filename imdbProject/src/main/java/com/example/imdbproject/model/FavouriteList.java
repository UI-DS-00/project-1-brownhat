package com.example.imdbproject.model;

import com.example.imdbproject.model.response.FavouriteListResponse;
import com.example.imdbproject.model.response.TitleBasicFavouriteList;
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
@Builder
public class FavouriteList {
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

     public FavouriteListResponse toResponse(FavouriteList favouriteList){
        FavouriteListResponse favouriteListResponse = new FavouriteListResponse();
        favouriteListResponse.setTitleBasicResponse(favouriteList.getTitleBasic().responseModel());
        favouriteListResponse.setName(favouriteList.getName());
        return favouriteListResponse;
    }

    public static TitleBasicFavouriteList showList (Set<FavouriteList> favouriteListSet){

        TitleBasicFavouriteList titleBasicFavouriteList = new TitleBasicFavouriteList();
        for (FavouriteList favouriteList1 : favouriteListSet) {
            titleBasicFavouriteList.getMovieName().add(favouriteList1.getTitleBasic().getPrimaryTitle());
            titleBasicFavouriteList.setName(favouriteList1.getName());
            titleBasicFavouriteList.setOwnerUsername(favouriteList1.getOwner().getUsername());

        }
            return titleBasicFavouriteList;
    }
}
