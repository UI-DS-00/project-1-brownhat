package com.example.imdbproject.repository;

import com.example.imdbproject.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface FavouriteListRepository extends JpaRepository<FavouriteList, Integer> {
    Optional<FavouriteList> findByName(String name);
    Set<FavouriteList> findByTitleBasic(TitleBasic titleBasic);
    Set<FavouriteList> findAllByNameAndOwner(String s1, AllUser s2);

    Optional<FavouriteList> findByOwnerAndName(AllUser s2, String s1);

    Set<FavouriteList> findAllByOwner(AllUser user);

    Optional<FavouriteList> findByOwnerAndNameAndTitleBasic(AllUser s1,String s2,TitleBasic titleBasic);
    Set<FavouriteList> findAllByTitleBasic(TitleBasic titleBasic);

}
