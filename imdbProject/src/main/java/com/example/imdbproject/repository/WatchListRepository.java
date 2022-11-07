package com.example.imdbproject.repository;


import com.example.imdbproject.model.WatchList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchListRepository extends JpaRepository<WatchList, Integer> {
}
