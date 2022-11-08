package com.example.imdbproject.repository;


import com.example.imdbproject.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RatingRepository extends JpaRepository<Rating, String> {

    Set<Rating> findAllByOrderByAverageRateDesc();

}
