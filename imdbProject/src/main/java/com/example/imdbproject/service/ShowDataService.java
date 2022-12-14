package com.example.imdbproject.service;

import com.example.imdbproject.model.FavouriteList;
import com.example.imdbproject.model.TitleBasic;
import com.example.imdbproject.model.response.NameBasicSummery;
import com.example.imdbproject.model.response.TitleBasicResponse;

import java.util.Set;

public interface ShowDataService {
    TitleBasicResponse allMoviesData(String tConst);

    Set <NameBasicSummery> ActorsAndDirectors (String professions);

    Set<TitleBasicResponse> allMoviesData();

    Set <TitleBasicResponse> filmEndYear();

    Set <TitleBasicResponse> filmRating();

    Set<TitleBasicResponse> topTen();

    Set<FavouriteList> favouriteList(TitleBasic titleBasic);


}


