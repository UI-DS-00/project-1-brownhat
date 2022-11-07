package com.example.imdbproject.repository;


import com.example.imdbproject.model.AllUser;
import com.example.imdbproject.model.PrimaryProfession;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface PrimaryProfessionRepository extends JpaRepository<PrimaryProfession, String> {

    Set<PrimaryProfession> findByProfession(String profession);

}