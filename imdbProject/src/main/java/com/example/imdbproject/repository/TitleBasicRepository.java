package com.example.imdbproject.repository;


import com.example.imdbproject.model.TitleBasic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface TitleBasicRepository extends CrudRepository<TitleBasic, String> {

    Set<TitleBasic> findAllByOrderByEndYearDesc();

}
