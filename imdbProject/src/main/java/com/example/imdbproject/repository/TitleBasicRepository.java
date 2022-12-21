package com.example.imdbproject.repository;


import com.example.imdbproject.model.TitleBasic;
import com.example.imdbproject.model.response.TitleBasicResponse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

public interface TitleBasicRepository extends CrudRepository<TitleBasic, String> {

    ArrayList<TitleBasic> findAllByOrderByEndYearDesc();

    Set<TitleBasic> findByEndYearIs(Integer endYear);
    Set<TitleBasic> findByGenres(String genre);

    Optional<TitleBasic> findByPrimaryTitle (String name);
    Set<TitleBasic> findTop10ByOrderByEndYearDesc();

}
