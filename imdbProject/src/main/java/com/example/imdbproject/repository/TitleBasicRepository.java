package com.example.imdbproject.repository;


import com.example.imdbproject.model.PrimaryProfession;
import com.example.imdbproject.model.TitleBasic;
import com.example.imdbproject.model.response.TitleBasicResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

import java.util.Optional;
import java.util.Set;

public interface TitleBasicRepository extends CrudRepository<TitleBasic, String> {

    Set<TitleBasic> findAllByOrderByEndYearDesc();

    //Set<Optional<TitleBasic>> findByEnd_year(int endYear);
    Set<TitleBasic> findByEndYearIs(Integer endYear);
    Set<TitleBasic> findByGenres(String genre);


}
