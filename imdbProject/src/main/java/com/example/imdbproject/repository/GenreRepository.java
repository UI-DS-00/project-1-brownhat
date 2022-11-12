package com.example.imdbproject.repository;

import com.example.imdbproject.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre,Long> {
}
