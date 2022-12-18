package com.example.imdbproject.repository;

import com.example.imdbproject.model.Genre;
import com.example.imdbproject.model.TitleBasic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


public interface GenreRepository extends JpaRepository<Genre,Long> {
    Set<TitleBasic> findAllByGenre(String genre);
}
