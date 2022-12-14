package com.example.imdbproject.service;

import com.example.imdbproject.model.response.TitleBasicResponse;

import java.util.Set;

public interface FilterService {

    Set<TitleBasicResponse> filterByYear(int year);

    Set<TitleBasicResponse> filterByGenre(String genre);

    Set<TitleBasicResponse> filterByActor(String nConst);

    Set<TitleBasicResponse> filterByRegion(String nConst);


}
