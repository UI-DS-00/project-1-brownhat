package com.example.imdbproject.repository;


import com.example.imdbproject.model.Rating;
import com.example.imdbproject.model.TitleBasic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Set;

public interface RatingRepository extends JpaRepository<Rating, String> {

    ArrayList<Rating> findAllByOrderByAverageRateDesc();

    ArrayList<Rating> findTop10ByOrderByAverageRateDesc();

    Rating findByTitleConst(TitleBasic titleBasic);

}
