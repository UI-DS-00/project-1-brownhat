package com.example.imdbproject.repository;


import com.example.dsproject.model.Crew;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrewRepository extends JpaRepository<Crew, Integer > {
}