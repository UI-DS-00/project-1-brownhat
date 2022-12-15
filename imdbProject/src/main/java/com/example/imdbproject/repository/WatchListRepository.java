package com.example.imdbproject.repository;


import com.example.imdbproject.model.AllUser;
import com.example.imdbproject.model.WatchList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import java.util.Optional;

public interface WatchListRepository extends JpaRepository<WatchList, Long> {

    Optional<WatchList> findByName(String name);
    Optional<WatchList> findByNameAndOwner( String watchName , AllUser name);
}
