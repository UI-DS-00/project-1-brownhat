package com.example.imdbproject.repository;



import com.example.imdbproject.model.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<Episode, Integer> {

    }


