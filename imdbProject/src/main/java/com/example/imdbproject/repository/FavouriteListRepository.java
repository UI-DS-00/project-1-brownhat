package com.example.imdbproject.repository;

import com.example.imdbproject.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

public interface FavouriteListRepository extends JpaRepository<FavouriteList, Integer> {
    Optional<FavouriteList> findByName(String name);
    Set<FavouriteList> findByTitleBasic(TitleBasic titleBasic);
    Set<FavouriteList> findAllByNameAndOwner(String s1, AllUser s2);


}
