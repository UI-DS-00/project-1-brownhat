package com.example.imdbproject.repository;



import com.example.imdbproject.model.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface EpisodeRepository extends JpaRepository<Episode, Integer> {

    }


