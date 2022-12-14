package com.example.imdbproject.repository;

import com.example.imdbproject.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface GenreRepository extends JpaRepository<Genre,Long> {
}
