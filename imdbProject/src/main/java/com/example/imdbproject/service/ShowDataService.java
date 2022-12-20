package com.example.imdbproject.service;

import com.example.imdbproject.model.response.NameBasicSummery;
import com.example.imdbproject.model.response.TitleBasicFavouriteList;
import com.example.imdbproject.model.response.TitleBasicResponse;

import java.util.ArrayList;
import java.util.Set;

public interface ShowDataService {
    TitleBasicResponse allMoviesData(String tConst);

    Set <NameBasicSummery> ActorsAndDirectors (String professions);

    Set<TitleBasicResponse> allMoviesData();

    Set <TitleBasicResponse> filmEndYear();

    Set <TitleBasicResponse> filmRating();

    ArrayList<TitleBasicResponse> topTen();

    Set<TitleBasicFavouriteList> othersFavouriteList(String titleBasic);


}


