package com.example.imdbproject.repository;

import com.example.imdbproject.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


public interface GenreRepository extends JpaRepository<Genre,Long> {
    Set<Genre> findGenreByGenre(String s);
}
