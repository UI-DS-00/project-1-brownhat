package com.example.imdbproject.model.response;

import com.example.imdbproject.model.FavouriteList;
import com.example.imdbproject.model.TitleBasic;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;

@Getter
@Setter
@Component
public class FavouriteListResponse implements Comparable {

    private String name;

    private TitleBasicResponse titleBasicResponse;

    @Override
    public int compareTo(Object o1) {

        FavouriteListResponse favouriteListResponse = (FavouriteListResponse) o1;
        return (this.getName().compareTo( favouriteListResponse.getName()));
    }
}
