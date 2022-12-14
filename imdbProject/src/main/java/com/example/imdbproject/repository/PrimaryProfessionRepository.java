package com.example.imdbproject.repository;


import com.example.imdbproject.model.PrimaryProfession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

public interface PrimaryProfessionRepository extends JpaRepository<PrimaryProfession, String> {

    Set<PrimaryProfession> findByProfession (String profession);

}