package com.example.imdbproject.repository;



import com.example.imdbproject.model.NameBasic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;


@EnableJpaRepositories
public interface NameBasicRepository extends JpaRepository<NameBasic, String> {


    Optional<NameBasic> findByPrimaryName(String name);

    Set<NameBasic> findByBirthDayAndBirthMonth(int i1,int i2);
}
