package com.example.imdbproject.repository;

import com.example.imdbproject.model.Aka;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

public interface AkaRepository extends JpaRepository<Aka, Long> {

    Set<Aka> findByRegion(String region);

}
