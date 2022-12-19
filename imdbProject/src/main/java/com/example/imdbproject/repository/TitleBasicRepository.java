package com.example.imdbproject.repository;


import com.example.imdbproject.model.TitleBasic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

public interface TitleBasicRepository extends CrudRepository<TitleBasic, String> {

    Set<TitleBasic> findAllByOrderByEndYearDesc();
    //Set<Optional<TitleBasic>> findByEnd_year(int endYear);
    Set<TitleBasic> findByEndYearIs(Integer endYear);
    Set<TitleBasic> findByGenres(String genre);

    Set<TitleBasic> findTop10ByOrderByEndYearDesc();

}
