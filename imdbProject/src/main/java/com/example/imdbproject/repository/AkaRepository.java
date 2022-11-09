package com.example.imdbproject.repository;

import com.example.imdbproject.model.Aka;
import com.example.imdbproject.model.TitleBasic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;


public interface AkaRepository extends JpaRepository<Aka, Long> {

    Set<Aka> findByRegion(String region);

}
