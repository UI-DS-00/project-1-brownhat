package com.example.imdbproject.repository;



import com.example.imdbproject.model.NameBasic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@EnableJpaRepositories
public interface NameBasicRepository extends JpaRepository<NameBasic, String> {

    //Set<NameBasic> findByKnownForTitles(String nConst);

    Optional<NameBasic> findByPrimaryName(String primaryName);
}
