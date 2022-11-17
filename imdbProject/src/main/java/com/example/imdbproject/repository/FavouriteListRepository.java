package com.example.imdbproject.repository;

import com.example.imdbproject.model.Episode;
import com.example.imdbproject.model.FavouriteList;
import com.example.imdbproject.model.TitleBasic;
import com.example.imdbproject.model.WatchList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

public interface FavouriteListRepository extends JpaRepository<FavouriteList, Integer> {
    Optional<FavouriteList> findByName(String name);
    Set<FavouriteList> findByList(TitleBasic titleBasic);

}
