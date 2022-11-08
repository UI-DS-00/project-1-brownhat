package com.example.imdbproject.service;

import com.example.imdbproject.model.NameBasic;
import com.example.imdbproject.model.response.NameBasicSummery;
import com.example.imdbproject.model.response.TitleBasicResponse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;

public interface ShowDataService {
    TitleBasicResponse allMoviesData(String tConst);

    Set <NameBasicSummery> ActorsAndDirectors (String professions);

    Set<TitleBasicResponse> allMoviesData ();




}


